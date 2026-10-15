# STEP Week 8 Assignment — CodInClub / BridgeLabz Category C Problems

This repository contains production-quality, fully verified Java solutions and comprehensive theoretical QLA (Quiz, Lab & Assessment) documentation for the **CodInClub (Powered by BridgeLabz) Category C Assignment Problems (Week 8)**.

The assignment focuses on core **Object-Oriented Programming (OOP)** design principles in Java:
- **Inheritance & Hierarchy Modeling:** Designing specialized subclasses from general abstract base types.
- **Runtime Polymorphism & Dynamic Method Dispatch:** Executing subtype-specific behaviors without conditional type checks (`if-else` / `switch` / `instanceof`).
- **Open/Closed Principle (OCP):** Keeping core processing engines closed for modification while easily extending them with new subtypes.
- **Liskov Substitution Principle (LSP):** Ensuring all subtypes fulfill the contract established by their base abstractions.
- **Subtype Encapsulation:** Isolating specialized parameters (e.g. `occupants` in `SharedRoom`) inside concrete subclasses without polluting the superclass contract.
- **Polymorphic Collections (`List<BaseType>`):** Managing heterogeneous object streams sequentially and processing them uniformly.

---

## Repository Contents

```
stepweek8-assignment/
├── .gitignore                                                        # Ignores .class and IDE artifacts
├── README.md                                                         # Assignment documentation & test guide
├── QLA_SOLUTIONS.md                                                  # Comprehensive QLA document (Quiz, Lab, Concepts)
├── CanteenBillingCounter.java                                        # Problem 1: Root source file
├── CampusParkingChargeCalculator.java                                # Problem 2: Root source file
├── HostelElectricityBillCalculator.java                              # Problem 3: Root source file
├── HostelElectricityBill.java                                        # Problem 3: Alias source file
├── FestivalBonusCalculator.java                                      # Problem 4: Root source file
├── StreamingPlanRenewalReminder.java                                 # Problem 5: Root source file
├── Problem1_TheCanteenBillingCounter/
│   └── CanteenBillingCounter.java                                    # Problem 1 package copy
├── Problem2_TheCampusParkingChargeCalculator/
│   └── CampusParkingChargeCalculator.java                            # Problem 2 package copy
├── Problem3_TheHostelElectricityBill/
│   ├── HostelElectricityBillCalculator.java                          # Problem 3 package copy
│   └── HostelElectricityBill.java                                    # Problem 3 alias copy
├── Problem4_TheFestivalBonusCalculator/
│   └── FestivalBonusCalculator.java                                  # Problem 4 package copy
└── Problem5_TheStreamingPlanRenewalReminder/
    └── StreamingPlanRenewalReminder.java                             # Problem 5 package copy
```

---

## Problem Summaries & Implementations

### Problem 1: The Canteen Billing Counter
- **Source Files:** [`CanteenBillingCounter.java`](./CanteenBillingCounter.java) | [`Problem1_TheCanteenBillingCounter/CanteenBillingCounter.java`](./Problem1_TheCanteenBillingCounter/CanteenBillingCounter.java)
- **Hierarchy:** `Customer` $\rightarrow$ `StudentCustomer`, `StaffCustomer`, `GuestCustomer`.
- **Key Method:** `public abstract double calculateFinalAmount();`
- **Business Rules:**
  - `STUDENT`: 10% discount on bill amount ($Amount \times 0.90$).
  - `STAFF`: 5% discount on bill amount ($Amount \times 0.95$).
  - `GUEST`: Full amount plus a ₹10 service charge ($Amount + 10.00$).
- **Sample Run:**
  ```text
  Input:
  3
  STUDENT 200
  STAFF 300
  GUEST 150

  Output:
  STUDENT: 180.00
  STAFF: 285.00
  GUEST: 160.00
  Total: 625.00
  ```

---

### Problem 2: The Campus Parking Charge Calculator
- **Source Files:** [`CampusParkingChargeCalculator.java`](./CampusParkingChargeCalculator.java) | [`Problem2_TheCampusParkingChargeCalculator/CampusParkingChargeCalculator.java`](./Problem2_TheCampusParkingChargeCalculator/CampusParkingChargeCalculator.java)
- **Hierarchy:** `Vehicle` $\rightarrow$ `Bike`, `Car`, `Truck`.
- **Key Method:** `public abstract double calculateCharge();`
- **Business Rules:**
  - `BIKE`: Flat ₹10 per hour.
  - `CAR`: ₹30 for the first hour, plus ₹20 for each additional hour.
  - `TRUCK`: ₹50 per hour, with a minimum charge of ₹100.
- **Sample Run:**
  ```text
  Input:
  4
  BIKE 3
  CAR 4
  TRUCK 1
  CAR 1

  Output:
  BIKE: 30.00
  CAR: 90.00
  TRUCK: 100.00
  CAR: 30.00
  Total: 250.00
  ```

---

### Problem 3: The Hostel Electricity Bill
- **Source Files:** [`HostelElectricityBillCalculator.java`](./HostelElectricityBillCalculator.java) | [`Problem3_TheHostelElectricityBill/HostelElectricityBillCalculator.java`](./Problem3_TheHostelElectricityBill/HostelElectricityBillCalculator.java)
- **Hierarchy:** `Room` $\rightarrow$ `SingleRoom`, `SharedRoom`, `AcRoom`.
- **Key Method:** `public abstract double calculateBill();`
- **Business Rules:**
  - `SINGLE`: ₹8 per unit.
  - `SHARED`: ₹6 per unit, divided equally among `occupants`.
  - `AC`: ₹10 per unit, plus a fixed charge of ₹200.
- **Design Highlight:** `SharedRoom` encapsulates the extra `occupants` property privately, preserving a zero-argument `calculateBill()` contract on the base `Room` class.
- **Sample Run:**
  ```text
  Input:
  3
  SINGLE 120
  SHARED 150 3
  AC 100

  Output:
  SINGLE: 960.00
  SHARED: 300.00
  AC: 1200.00
  Total: 2460.00
  ```

---

### Problem 4: The Festival Bonus Calculator
- **Source Files:** [`FestivalBonusCalculator.java`](./FestivalBonusCalculator.java) | [`Problem4_TheFestivalBonusCalculator/FestivalBonusCalculator.java`](./Problem4_TheFestivalBonusCalculator/FestivalBonusCalculator.java)
- **Hierarchy:** `Employee` $\rightarrow$ `FullTimeEmployee`, `PartTimeEmployee`, `InternEmployee`.
- **Key Method:** `public abstract double calculateBonus();`
- **Business Rules:**
  - `FULLTIME`: 10% of monthly salary.
  - `PARTTIME`: 5% of monthly salary.
  - `INTERN`: Flat ₹2,000 bonus, independent of salary.
- **Sample Run:**
  ```text
  Input:
  3
  FULLTIME Asha 50000
  PARTTIME Ravi 30000
  INTERN Neha 15000

  Output:
  Asha: 5000.00
  Ravi: 1500.00
  Neha: 2000.00
  Total Bonus: 8500.00
  ```

---

### Problem 5: The Streaming Plan Renewal Reminder
- **Source Files:** [`StreamingPlanRenewalReminder.java`](./StreamingPlanRenewalReminder.java) | [`Problem5_TheStreamingPlanRenewalReminder/StreamingPlanRenewalReminder.java`](./Problem5_TheStreamingPlanRenewalReminder/StreamingPlanRenewalReminder.java)
- **Hierarchy:** `Subscription` $\rightarrow$ `BasicSubscription`, `StandardSubscription`, `PremiumSubscription`.
- **Key Method:** `public abstract int getValidityDays();` and `calculateRenewalDate()`.
- **Business Rules:**
  - `BASIC`: Valid for 30 days.
  - `STANDARD`: Valid for 90 days.
  - `PREMIUM`: Valid for 365 days.
  - Renewal date is start date plus validity days via `java.time.LocalDate.plusDays()`. Correctly handles leap years (e.g. 2024) and year transitions.
- **Sample Run:**
  ```text
  Input:
  4
  BASIC Asha 2024-01-15
  STANDARD Ravi 2024-02-01
  PREMIUM Neha 2024-03-10
  BASIC Kiran 2024-12-20

  Output:
  Asha: 2024-02-14
  Ravi: 2024-05-01
  Neha: 2025-03-10
  Kiran: 2025-01-19
  ```

---

## Compilation & Execution Guide

### Compiling All Programs
```bash
javac CanteenBillingCounter.java CampusParkingChargeCalculator.java HostelElectricityBillCalculator.java FestivalBonusCalculator.java StreamingPlanRenewalReminder.java
```

### Running Each Program Interactively
```bash
# Problem 1
java CanteenBillingCounter

# Problem 2
java CampusParkingChargeCalculator

# Problem 3
java HostelElectricityBillCalculator

# Problem 4
java FestivalBonusCalculator

# Problem 5
java StreamingPlanRenewalReminder
```

### Running with Piped Input (PowerShell)
```powershell
# Problem 1
@"
3
STUDENT 200
STAFF 300
GUEST 150
"@ | java CanteenBillingCounter

# Problem 2
@"
4
BIKE 3
CAR 4
TRUCK 1
CAR 1
"@ | java CampusParkingChargeCalculator

# Problem 3
@"
3
SINGLE 120
SHARED 150 3
AC 100
"@ | java HostelElectricityBillCalculator

# Problem 4
@"
3
FULLTIME Asha 50000
PARTTIME Ravi 30000
INTERN Neha 15000
"@ | java FestivalBonusCalculator

# Problem 5
@"
4
BASIC Asha 2024-01-15
STANDARD Ravi 2024-02-01
PREMIUM Neha 2024-03-10
BASIC Kiran 2024-12-20
"@ | java StreamingPlanRenewalReminder
```

---

## Architectural Highlights

1. **Zero Conditional Dispatch in Processing Engines:** None of the calculation loops use `if-else if` or `instanceof` statements. Instead, the Java Virtual Machine dispatches method calls polymorphically at runtime via the virtual method table (`invokevirtual`).
2. **Encapsulated State Invariants:** Base classes declare all state attributes as `private final` with accessor methods, preventing direct external mutation and ensuring thread-safe immutability.
3. **Robust Input Parsing:** Uses `java.util.Scanner` with Locale-independent parsing (`Locale.US`) to guarantee that floating-point outputs are consistently formatted with period decimal separators across all system locales.
4. **Calendar Safety:** Utilizes modern ISO-8601 `java.time.LocalDate` rather than legacy error-prone calendar arithmetic.
