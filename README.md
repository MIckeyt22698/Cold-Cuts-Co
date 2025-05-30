## Features

* **Custom Sandwich Builder:**
    * Step-by-step guided process for selecting bread type and sandwich size.
    * Categorized topping selection (Meats, Cheeses, Veggies/Other, Sauces) for easy Browse.
    * Ability to add multiple toppings from each category.
    * Streamlined navigation between topping categories (just press Enter to move to the next).
    * Option to toast the sandwich.
* **Signature Sandwiches:**
    * Choose from pre-defined signature sandwiches (e.g., Philly Cheesesteak, Italian, Veggie Delight).
    * Signature sandwiches come with default toppings but can be further customized.
* **Order Management:**
    * Add sandwiches, drinks, and chips to the current order.
    * View a summary of the current order at any time.
    * Option to remove items from the order.
* **Checkout & Receipt:**
    * Calculate the total cost of the order.
    * Generate a detailed receipt displayed in the console.
    * Option to cancel the entire order.
* **User-Friendly Console Interface:**
    * Clear prompts and instructional messages.
    * Utilizes console colors for enhanced readability and visual appeal.
    * Robust input handling to prevent errors and guide the user.
 



**Strong OOP Paradigm:**
    * Core entities like `Order`, `Sandwich`, `Topping`, `Drink`, and `Chip` are represented by dedicated classes, encapsulating their data and behavior.
    * **Inheritance** is used for `SignatureSandwich` extending `Sandwich`, and specific signature sandwiches extending `SignatureSandwich`, promoting code reuse.
    * **Polymorphism** is achieved through an `OrderableItem` interface (implemented by `Sandwich`, `Drink`, `Chip`), allowing the `Order` to uniformly calculate prices for any item type.
* **Separation of Concerns:**
    * UI logic (`AddSandwichScreen`, `OrderScreen`, etc.) is distinct from core business logic (model classes like `Sandwich`, `Order`).
    * Utility classes (`InputHelper`, `ConsoleColors`, `Menu`) handle specific cross-cutting concerns like input validation, console formatting, and data provisioning, keeping the main logic clean.
* **Streamlined Console UX:**
    * Implemented a guided, step-by-step ordering flow.
    * Toppings are categorized and presented clearly. A significant UX improvement was allowing users to simply press **ENTER** to move between topping categories, reducing unnecessary typing.
    * Clear and consistent feedback messages guide the user through the process.
 



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
