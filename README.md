# PatternGame
A simple game design based OO Design Patterns

While going through "Head First: Design Patterns", I wanted to practice the different patterns. It seemed that a character creator was a good platform in order to try this out.

 - This list shows examples where different design patterns are used. This list does not show everywhere I used a pattern.

Strategy Pattern - "com/cooper/models/character/Character" abstract class - All characters can attack and defend. However, the weapon and armor used to perform these actions can change at run time.

Decorator Pattern - "com/cooper/models/{weapon|armor}/decorators/{WeaponDecorator|ArmorDecorator}" abstract classes - Weapons (such as "Dagger" and "Great Sword") can be augmented, changing both the base stats and names ("sword" + "swift" + "of the bear" = "swift sword of the bear"). Multiple augments (including redundant augments) can be placed on a single weapon/armor, so the decorator makes sense.

Singleton Pattern - "com/cooper/ActiveCharacterSingleton" class - A single location that could be referenced for active characters was need. This probable could have been a spring bean instead, but I wanted to try out the singleton method. I may use this same pattern for weapon and armor (and their decorators) that have been loaded in. However, if the list ever gets past a certain size, it would have to be removed again.

Builder Pattern - "com/cooper/builder/CarryableBuilder" class - Both usable weapons and armor can be constructed from a single base type, and any combination of decorators built upon that item. The builder pattern yields the approach of builder a composite item from a combination of smaller parts.

Abstract Factory and the Factory Pattern - "com/amobee/factory/AbstractCarryableFactory<T extends Carryable>" abstract factory - There is a factory builder which returns a factory for either weapon or armor, as these two are constructed in a very similar manner.

 - This list has unused patterns (which were shown in the book), along with potential ideas of how to make use of them.
 
 Observer Pattern - I may use this with active NPCs in order to have them react to global events
 
 Command Pattern - This may be used for special skills and abilities the characters have.
