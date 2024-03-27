public class a {
    public static int recursion(int aa[] , int n)
    {
        if (n==aa.length)
        return 0;

        if (aa[n]%2==0) {
            return aa[n] + recursion(aa , n+1);             
        }
        else
        return  recursion(aa , n+1);
    }

    public static void main(String args[]){
        int []a = {1,3,5,12,45,95};
        System.out.println(recursion(a, 0));
    }
}
