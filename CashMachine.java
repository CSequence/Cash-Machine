import java.util.Scanner;

public class CashMachine
{
    public static void main(String[] args)
    {
        int total, amount;
        boolean check = true;
        Scanner input = new Scanner(System.in);
        Slots slots = new Slots();

        System.out.println("CASH MACHINE");
        total = deposit(input);
        System.out.println();

        while (check)
        {
            System.out.print("Press ENTER to spin or 0 to cash out. ");
            check = spinCheck(input);
            if (check && total >= 10)
            {
                amount = slots.spin();
                total = total - 10 + amount;
                System.out.println("You won $" + amount);
                System.out.println("Total: $" + total + "\n");
                
            }
            else if (check && total < 10)
            {
                System.out.println("Insufficient funds!");
                check = false;
            }
            else
            {
                System.out.println("Thanks for playing!");
                check = false;
            }
        }

        input.close();
        System.out.println("Total: $" + total);
    }

    public static int deposit(Scanner input)
    {
        System.out.print("Enter amount: $");
        int total = input.nextInt();
        input.nextLine();
        return total;
    }

    // Checks whether user input is ENTER or 0.
    public static boolean spinCheck(Scanner input)
    {
        String userInput = input.nextLine();
        if (userInput.isEmpty() || !userInput.equals("0"))
            return true;
        else
            return false;
    }
}