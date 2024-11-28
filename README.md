# Data-Structures-Project

Personal Repo for transfer to class submission

KEVIN J GOMEZ GUZMAN||
SOLIMAR CRUZ HERNANDEZ||
JORGE L DELEON-ORAMA||

Our program implements a Seat Reservation System for a baseball stadium. We allow users to easily manage seat availability, make reservations, handle cancellations, manage waitlists for sold-out sections, see their reservations , and more. The system provides users with the ability to view available sections and seats, check the cost of seats, and reserve them for clients while keeping track of the transactions made . When a section is full, clients can be added to a waitlist if they wish to do so, and the system will automatically assign available seats to the first client in line once that seats reservation is canceled.
We used many data structures to optimize the performance and functionality of our code. We used a HashSet, a set, to store available seats, ensuring no duplicates. We then used a Hashmap ,a map, with keys of clients assigned to a set of seats. This was done to associate clients with their many reserved seats and  for quick retrieval of a clients reserved seats. We implemented a LinkedList used as a Qeue  to implement the waitlist.This waitlist follows the First-In-First-Out principle. Finally, we used a Stack to enable the cancellation functionality, allowing the user to reverse the most recent reservation. 

