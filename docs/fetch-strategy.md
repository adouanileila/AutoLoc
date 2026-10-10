
# Stratégies Fetch et Cascade — AutoLoc

## 1. Objectif

Cette note explique les choix de chargement des associations JPA et de propagation des opérations dans le projet AutoLoc.

## 2. Stratégies utilisées

| Association | Fetch | Cascade | Justification |
|---|---|---|---|
| Contrat → Paiement | LAZY | ALL + orphanRemoval | Un paiement dépend de son contrat. Sa suppression est propagée lorsque le contrat est supprimé ; retirer un paiement de la collection le supprime également en base. |
| Agence → Vehicule | LAZY | Aucune | Un véhicule peut survivre à son agence. |
| Agence → Employe | LAZY | Aucune | Les employés ont leur propre cycle de vie. |
| Vehicule ↔ Equipement | LAZY | Aucune | Les équipements peuvent être partagés entre plusieurs véhicules. |
| Client → Reservation | LAZY | PERSIST | L'enregistrement d'un client peut enregistrer ses nouvelles réservations. |
| Reservation → Vehicule | LAZY | Aucune | La réservation ne doit pas supprimer le véhicule associé. |
| Reservation ↔ Contrat | LAZY | ALL côté Reservation | Les opérations sur la réservation sont propagées à son contrat. |
| Vehicule → Maintenance | LAZY | PERSIST | L'enregistrement d'un véhicule peut enregistrer ses nouvelles maintenances. |

## 3. FetchType.LAZY

LAZY signifie que l'association est chargée lorsqu'elle est utilisée, plutôt que systématiquement avec l'entité principale.

Cette stratégie limite le chargement inutile des objets associés. Toutefois, accéder à une association non chargée après la fermeture du contexte de persistance peut provoquer une LazyInitializationException.

## 4. CascadeType

- PERSIST : propage l'enregistrement aux nouveaux objets associés.
- ALL : propage toutes les opérations couvertes par CascadeType.ALL.
- Aucune cascade : évite de propager involontairement les opérations à une entité qui possède son propre cycle de vie.

## 5. Conclusion

Les associations du projet AutoLoc utilisent LAZY pour limiter les chargements inutiles et des stratégies de cascade adaptées au cycle de vie des entités associées.