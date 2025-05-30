# Cold-Cuts-Co.
```mermaid
classDiagram
    direction LR

    class Program {
        +main()
    }

    class HomeScreen {
        +display()
    }

    class OrderScreen {
        +display()
        +handleRemoveItem()
        +handleCheckout()
    }

    class AddSandwichScreen {
        +display()
        -createCustomSandwich()
        -selectSignatureSandwich()
        -customizeSandwich(Sandwich)
        -addToppingToSandwich(Sandwich)
        -removeToppingFromSandwich(Sandwich)
        -handleToppingCategoryInput(Sandwich, String)
        -getBreadTypeFromUser()
        -getSandwichSizeFromUser()
    }

    class AddDrinkScreen {
        +display()
    }

    class AddChipsScreen {
        +display()
    }

    class CheckoutScreen {
        +display()
    }

    class Order {
        -items: List~OrderableItem~
        +addItem(OrderableItem)
        +addDrink(Drink)
        +addSandwich(Sandwich)
        +addChips(Chips)
        +calculateTotal()
        +toString()
    }

    class OrderableItem {
        <<interface>>
        +getName()
        +getPrice()
    }

    class Sandwich {
        -breadType: BreadType
        -size: Size
        -toasted: boolean
        -toppings: List~Topping~
        +getName()
        +getPrice()
        +addTopping(Topping)
        +removeTopping(Topping)
        +toString()
    }

    class SignatureSandwich {
        <<abstract>>
        #signatureName: String
        +getSignatureName()
        #buildToppings()
    }

    class PhillyCheesesteakSandwich {
        +PhillyCheesesteakSandwich(Size, boolean)
    }
    class ItalianSandwich {
        +ItalianSandwich(Size, boolean)
    }
    class VeggieDelightSandwich {
        +VeggieDelightSandwich(Size, boolean)
    }

    class Topping {
        -name: String
        -price: double
    }

    class Drink {
        -size: Size
        -flavor: String
    }

    class Chips {
        -flavor: String
    }

    class BreadType {
        <<enum>>
    }

    class Size {
        <<enum>>
    }

    class ReceiptService {
        +saveReceipt(Order)
    }

    class Menu {
        +static getAllAvailableToppings()
        +static getToppingByName(String)
        +static getAllAvailableDrinks()
        +static getAllAvailableChips()
    }

    class InputHelper {
        +static getString(String)
        +static getInt(String)
        +static getDouble(String)
        +static closeScanner()
    }

    class ConsoleColors {
        +static RESET
        +static BRIGHT_RED
        % etc.
    }

    %% Relationships
    Program --> HomeScreen : starts
    HomeScreen --> OrderScreen : creates/navigates

    OrderScreen --> AddSandwichScreen : creates/navigates
    OrderScreen --> AddDrinkScreen : creates/navigates
    OrderScreen --> AddChipsScreen : creates/navigates
    OrderScreen --> CheckoutScreen : creates/navigates
    OrderScreen --> Order : manages
    OrderScreen --> ReceiptService : uses

    AddSandwichScreen --> Sandwich : creates/customizes
    AddSandwichScreen --> Menu : uses data
    AddSandwichScreen --> InputHelper : gets input
    AddSandwichScreen --> ConsoleColors : applies colors

    Order --o OrderableItem : contains 0..*
    OrderableItem <|-- Sandwich
    OrderableItem <|-- Drink
    OrderableItem <|-- Chips

    Sandwich --o Topping : contains 0..*
    Sandwich -- BreadType : uses
    Sandwich -- Size : uses

    Sandwich <|-- SignatureSandwich
    SignatureSandwich <|-- PhillyCheesesteakSandwich
    SignatureSandwich <|-- ItalianSandwich
    SignatureSandwich <|-- VeggieDelightSandwich

    ReceiptService --> Order : processes
    Menu --> Topping : provides
    Menu --> Drink : provides
    Menu --> Chips : provides