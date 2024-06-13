package cd_programs;

import java.util.Scanner;
class ProductionRule
{
      String left,right;
      ProductionRule(String left,String right)
      {
        this.left = left;
        this.right = right;
       }
 }

 public class ShiftReduceParser
 {
  public static void main(String[] args)
  {
        Scanner sc = new Scanner(System.in);
        String input, stack = "";
        System.out.println("Enter the number of production rules: ");
        int n = sc.nextInt();
        sc.nextLine();
        ProductionRule[] rules = new ProductionRule[n];
        System.out.println("Enter the production rules (in the form 'left->right'): ");
        
        for (int i = 0; i < n; i++) 
        {
            String[] temp = sc.nextLine().split("->");
            rules[i] = new ProductionRule(temp[0], temp[1]);
        }
        
        System.out.println("Enter the input string: ");
        input = sc.nextLine();
        int i = 0;
       
        while (true) 
        {
            if (i < input.length()) 
            {
                char ch = input.charAt(i);
                i++;
                stack += ch;
                System.out.print(stack + "\t");
                System.out.print(input.substring(i) + "\tShift " + ch + "\n");
            }
            
            for (int j = 0; j < n; j++) 
            {
                int substringIndex = stack.indexOf(rules[j].right);
                if (substringIndex != -1)
                {
                    stack = stack.substring(0, substringIndex) + rules[j].left;
                    System.out.print(stack + "\t");
                    System.out.print(input.substring(i) + "\tReduce " + rules[j].left + "->" + rules[j].right + "\n");
                    j = -1; 
                }
            }
            
            if (stack.equals(rules[0].left) && i == input.length()) 
            {
                System.out.println("\nACCEPT");
                break;
            }
            
            if (i == input.length())
            {
                System.out.println("\nERROR");
                break;
            }
        }           
    }
 }