import java.util.Scanner;

class Solution
{
    public static void main(String args[])
    {
        Scanner sc = new Scanner(System.in);
        
        int n = sc.nextInt();
        double d = sc.nextDouble();
        sc.nextLine();
        String s = sc.nextLine();
        
        sc.close();
        
        System.out.println("String: " + s);
        System.out.println("Double: " + d);
        System.out.println("Int: " + n);
    }
}