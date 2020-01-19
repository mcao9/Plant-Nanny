public class Testing
{
    public static void main(String[] args)
    {
        PlantStatSet plantStatSet = new PlantStatSet();

        plantStatSet.readPort();

        System.out.println(plantStatSet.toString());
    }
}
