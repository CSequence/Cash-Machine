import java.util.Random;

public class Slots
{
    private String slotA, slotB, slotC;
    private Random rand = new Random();

    // ----- +++++ 1,1,1,1 2,2,2 5,5 10
    private String spinA(Random rand)
    {
        int roll = rand.nextInt(20);
        if (roll == 19)
            return "10";
        else if (roll >= 17)
            return "5";
        else if (roll >= 14)
            return "2";
        else if (roll >= 10)
            return "1";
        else if (roll >= 5)
            return "+";
        else
            return "-";
    /*
        switch (rand.nextInt(6))
        {
            case 0:
                return "-";
            case 1:
                return "+";
            case 2:
                return "1";
            case 3:
                return "2";
            case 4:
                return "5";
            case 5:
                return "10";
            default:
                return "";
        }
    */
    }

    // ---- ++++ 5,5 0,0
    private String spinB(Random rand)
    {
        int roll = rand.nextInt(12);
        if (roll >= 10)
            return "0";
        else if (roll >= 8)
            return "5";
        else if (roll >= 4)
            return "+";
        else
            return "-";
    /*
        switch (rand.nextInt(4))
        {
            case 0:
                return "-";
            case 1:
                return "+";
            case 2:
                return "5";
            case 3:
                return "0";
            default:
                return "";
        }
    */
    }

    // ---- ++++ 5,5,5 0,0,0 00
    private String spinC(Random rand)
    {
        int roll = rand.nextInt(15);
        if (roll == 14)
            return "00";
        else if (roll >= 11)
            return "0";
        else if (roll >= 8)
            return "5";
        else if (roll >= 4)
            return "+";
        else
            return "-";
    /*
        switch (rand.nextInt(5))
        {
            case 0:
                return "-";
            case 1:
                return "+";
            case 2:
                return "5";
            case 3:
                return "0";
            case 4:
                return "00";
            default:
                return "";
        }
    */
    }

    private int calculate()
    {
        String result = "";
        if (!slotA.equals("-") && !slotA.equals("+"))
            result += slotA;
        if (!slotB.equals("-") && !slotB.equals("+"))
            result += slotB;
        if (!slotC.equals("-") && !slotC.equals("+"))
            result += slotC;

        if (result.isEmpty())
            return 0;
        else
            return Integer.parseInt(result);
    }

    // Checks for a 0 or 00.
    private boolean respinCheck()
    {
        if (slotA.equals("1") || slotA.equals("2") || slotA.equals("5") || slotA.equals("10") || slotB.equals("5") || slotC.equals("5"))
            return false;
        else if (slotB.equals("0") || slotC.equals("0") || slotC.equals("00"))
            return true;
        else
            return false;
    }

    private void respin(Random rand)
    {
        boolean check = respinCheck();
        while (check)
        {
            check = false;
            System.out.println("RESPIN");

            if (slotB.equals("0"))
            {
                if (!slotC.equals("0") && !slotC.equals("00"))
                {
                    slotC = spinC(rand);
                    if (slotC.equals("0") || slotC.equals("00"))
                        check = true;
                    System.out.println(slotA + "\t" + slotB + "\t" + slotC);
                }

                slotA = spinA(rand);
                if (slotA.equals("1") || slotA.equals("2") || slotA.equals("5") || slotA.equals("10"))
                    check = false;
                System.out.println(slotA + "\t" + slotB + "\t" + slotC);
            }

            else if (slotC.equals("0") || slotC.equals("00"))
            {
                if (!slotB.equals("0"))
                {
                    slotB = spinB(rand);
                    if (slotB.equals("0"))
                        check = true;
                    System.out.println(slotA + "\t" + slotB + "\t" + slotC);
                }

                slotA = spinA(rand);
                if (slotA.equals("1") || slotA.equals("2") || slotA.equals("5") || slotA.equals("10"))
                    check = false;
                System.out.println(slotA + "\t" + slotB + "\t" + slotC);
            }
        }
    }

    private boolean redCheck()
    {
        int count = 0;
        if (slotA.equals("+"))
            count += 1;
        if (slotB.equals("+"))
            count += 1;
        if (slotC.equals("+"))
            count += 1;

        if (calculate() > 1 && count > 1)
            return true;
        else
            return false;
    }

    private void red(Random rand)
    {
        // Chance for red respin.
        boolean chance;
        // 50%
        if (rand.nextInt(2) == 0)
            chance = true;
        else
            chance = false;

        boolean check = redCheck();

        // Chooses which slot to guarantee a better roll.
        int guarantee = rand.nextInt(2);

        if (check && chance)
        {
            System.out.println("RED RESPIN");
            // Respin B and C
            if (!slotA.equals("+"))
            {
                if (guarantee == 0)
                {
                    while (slotB.equals("-") || slotB.equals("+"))
                    {
                        slotB = spinB(rand);
                    }
                    System.out.println(slotA + "\t" + slotB + "\t" + slotC);
                    slotC = spinC(rand);
                    System.out.println(slotA + "\t" + slotB + "\t" + slotC);
                }
                else
                {
                    slotB = spinB(rand);
                    System.out.println(slotA + "\t" + slotB + "\t" + slotC);
                    while (slotC.equals("-") || slotC.equals("+"))
                    {
                        slotC = spinC(rand);
                    }
                    System.out.println(slotA + "\t" + slotB + "\t" + slotC);
                }
            }
            // Respin A and C
            else if (!slotB.equals("+"))
            {
                if (guarantee == 0)
                {
                    while (slotA.equals("-") || slotA.equals("+"))
                    {
                        slotA = spinA(rand);
                    }
                    System.out.println(slotA + "\t" + slotB + "\t" + slotC);
                    slotC = spinC(rand);
                    System.out.println(slotA + "\t" + slotB + "\t" + slotC);
                }
                else
                {
                    slotA = spinA(rand);
                    System.out.println(slotA + "\t" + slotB + "\t" + slotC);
                    while (slotC.equals("-") || slotC.equals("+"))
                    {
                        slotC = spinC(rand);
                    }
                    System.out.println(slotA + "\t" + slotB + "\t" + slotC);
                }
            }
            // Respin A and B
            else
            {
                if (guarantee == 0)
                {
                    while (slotA.equals("-") || slotA.equals("+"))
                    {
                        slotA = spinA(rand);
                    }
                    System.out.println(slotA + "\t" + slotB + "\t" + slotC);
                    slotB = spinB(rand);
                    System.out.println(slotA + "\t" + slotB + "\t" + slotC);
                }
                else
                {
                    slotA = spinA(rand);
                    while (slotB.equals("-") || slotB.equals("+"))
                    {
                        slotB = spinB(rand);
                    }
                    System.out.println(slotA + "\t" + slotB + "\t" + slotC);

                    if (slotB.equals("0") && (slotA.equals("-") || slotA.equals("+")))
                    {
                        while (slotA.equals("-") || slotA.equals("+"))
                        {
                            slotA = spinA(rand);
                        }
                    }
                    System.out.println(slotA + "\t" + slotB + "\t" + slotC);
                }
            }
        }
    }

    public int spin()
    {
        slotA = spinA(rand);
        slotB = spinB(rand);
        slotC = spinC(rand);

        System.out.println(slotA + "\t" + slotB + "\t" + slotC);
        respin(rand);
        red(rand);
        /*
        if (respinCheck())
            System.out.println("REROLL");

        if (redCheck())
        {
            System.out.println("RED REROLL");
            red(rand);
        }
        */
        return calculate();
    }
}