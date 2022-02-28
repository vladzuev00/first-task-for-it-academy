package by.itacademy.zuevvlad.bankaccountproject.service.entityspecification;

import by.itacademy.zuevvlad.bankaccountproject.entity.Entity;

@FunctionalInterface
public interface EntitySpecification<TypeOfResearchEntity extends Entity>
{
    public abstract boolean isMatch(final TypeOfResearchEntity researchEntity);
}
