import java.util.ArrayList;

/**
 * The StopAndFrisk class represents stop-and-frisk data, provided by
 * the New York Police Department (NYPD), that is used to compare
 * during when the policy was put in place and after the policy ended.
 * 
 * @author Tanvi Yamarthy
 * @author Vidushi Jindal
 */
public class StopAndFrisk {

    /*
     * The ArrayList keeps track of years that are loaded from CSV data file.
     * Each SFYear corresponds to 1 year of SFRecords. 
     * Each SFRecord corresponds to one stop and frisk occurrence.
     */ 
    private ArrayList<SFYear> database; 

    /*
     * Constructor creates and initializes the @database array
     * 
     * DO NOT update nor remove this constructor
     */
    public StopAndFrisk () {
        database = new ArrayList<>();
    }

    /*
     * Getter method for the database.
     * *** DO NOT REMOVE nor update this method ****
     */
    public ArrayList<SFYear> getDatabase() {
        return database;
    }

    /**
     * This method reads the records information from an input csv file and populates 
     * the database.
     * 
     * Each stop and frisk record is a line in the input csv file.
     * 
     * 1. Open file utilizing StdIn.setFile(csvFile)
     * 2. While the input still contains lines:
     *    - Read a record line (see assignment description on how to do this)
     *    - Create an object of type SFRecord containing the record information
     *    - *****If the record's year has already is present in the database:
     *        - Add the SFRecord to the year's records
     *    - If the record's year is not present in the database:
     *        - Create a new SFYear 
     *        - Add the SFRecord to the new SFYear
     *        - Add the new SFYear to the database ArrayList
     * 
     * @param csvFile
     */
    public void readFile ( String csvFile ) {

        // DO NOT remove these two lines
        StdIn.setFile(csvFile); // Opens the file
        StdIn.readLine();       // Reads and discards the header line

        // WRITE YOUR CODE HERE
        while(StdIn.hasNextLine()){
            String[] recordEntries = StdIn.readLine().split(",");
            int year = Integer.parseInt(recordEntries[0]);
            String description = recordEntries[2];
            boolean arrested = recordEntries[13].equals("Y");
            boolean frisked = recordEntries[16].equals("Y");
            String gender = recordEntries[52];
            String race = recordEntries[66];
            String location = recordEntries[71];

            SFRecord SFRecod_object = new SFRecord(description, arrested, frisked, gender, race, location);

            if (database.size() == 0)
            {
                SFYear year_object = new SFYear(year);
                year_object.addRecord(SFRecod_object);
                database.add(year_object);
            }
            else
            {
                for(SFYear x : database)
                {
                    if(year == x.getcurrentYear())
                    {
                        x.addRecord(SFRecod_object);
                    }
                    else
                    {
                        SFYear year_object = new SFYear(year);
                        year_object.addRecord(SFRecod_object);
                        database.add(year_object);
                        break;
                    }
                }
            }
        }
    }

    /**
     * This method returns the stop and frisk records of a given year where 
     * the people that was stopped was of the specified race.
     * 
     * @param year we are only interested in the records of year.
     * @param race we are only interested in the records of stops of people of race. 
     * @return an ArrayList containing all stop and frisk records for people of the 
     * parameters race and year.
     */

    public ArrayList<SFRecord> populationStopped ( int year, String race ) {

        // WRITE YOUR CODE HERE
        ArrayList<SFRecord> stop_frisk_ArrayList = new ArrayList<>();
        for(SFYear SF_years : database)
        {
            if(SF_years.getcurrentYear() == year)
            {
                for(SFRecord records : SF_years.getRecordsForYear())
                {
                    if(race.equals(records.getRace()))
                    {
                        stop_frisk_ArrayList.add(records);
                    }
                }
            }
        }
        return stop_frisk_ArrayList;
    }

    /**
     * This method computes the percentage of records where the person was frisked and the
     * percentage of records where the person was arrested.
     * 
     * @param year we are only interested in the records of year.
     * @return the percent of the population that were frisked and the percent that
     *         were arrested.
     */
    public double[] friskedVSArrested ( int year ) {
        
        // WRITE YOUR CODE HERE
        int frisked_counter = 0;
        int arrested_counter = 0;
        double frisked_percentage = 0;
        double arrested_percentage = 0;

        for(SFYear year_object : database)
        {
            if(year_object.getcurrentYear() == year)
            {
                for(SFRecord records : year_object.getRecordsForYear())
                {
                    if(records.getFrisked() == true)
                    {
                        frisked_counter++;
                    }
                    if(records.getArrested() == true)
                    {
                        arrested_counter++;
                    }
                }

                frisked_percentage = frisked_counter / (double) year_object.getRecordsForYear().size() * 100;
                arrested_percentage = arrested_counter / (double) year_object.getRecordsForYear().size() * 100;

            }
        }

        double[] frisked_arrested_array = {frisked_percentage , arrested_percentage};

        return frisked_arrested_array; // update the return value
    }

    /**
     * This method keeps track of the fraction of Black females, Black males,
     * White females and White males that were stopped for any reason.
     * Drawing out the exact table helps visualize the gender bias.
     * 
     * @param year we are only interested in the records of year.
     * @return a 2D array of percent of number of White and Black females
     *         versus the number of White and Black males.
     */
    public double[][] genderBias ( int year ) {

        // WRITE YOUR CODE HERE
        int whiteFemale_count = 0;
        int blackFemale_count = 0;
        int whiteMale_count = 0;
        int blackMale_count = 0;
        int totalBlack_population = 0;
        int totalWhite_population = 0;
        double whiteFemale_percentage = 0;
        double blackFemale_percentage = 0;
        double whiteMale_percentage = 0;
        double blackMale_percentage = 0;
        double totalFemale_percentage = 0;
        double totalMale_percentage = 0;

        for(SFYear year_object : database)
        {
            if(year == year_object.getcurrentYear())
            {
                for(SFRecord record : year_object.getRecordsForYear())
                {
                    if((record.getRace()).equals("B"))
                    {
                        if(record.getGender().equals("F"))
                        {
                            blackFemale_count++;
                        }
                        else if(record.getGender().equals("M"))
                        {
                            blackMale_count++;
                        }
                        totalBlack_population++;
                    }
                    else if((record.getRace()).equals("W"))
                    {
                        if(record.getGender().equals("F"))
                        {
                            whiteFemale_count++;
                        }
                        else if(record.getGender().equals("M"))
                        {
                            whiteMale_count++;
                        }
                        totalWhite_population++;
                    }
                }

                blackFemale_percentage = (double) blackFemale_count / totalBlack_population * 100 *0.5;
                blackMale_percentage = (double) blackMale_count / totalBlack_population * 100 *0.5;
                whiteFemale_percentage = (double) whiteFemale_count / totalWhite_population * 100 *0.5;
                whiteMale_percentage = (double) whiteMale_count / totalWhite_population * 100 *0.5;

                totalFemale_percentage = blackFemale_percentage + whiteFemale_percentage;
                totalMale_percentage = blackMale_percentage + whiteMale_percentage;
            }
        }

        double[][] genderBias_array = {{blackFemale_percentage , whiteFemale_percentage , totalFemale_percentage} , {blackMale_percentage , whiteMale_percentage , totalMale_percentage}};
        return genderBias_array; // update the return value
    }

    /**
     * This method checks to see if there has been increase or decrease 
     * in a certain crime from year 1 to year 2.
     * 
     * Expect year1 to preceed year2 or be equal.
     * 
     * @param crimeDescription
     * @param year1 first year to compare.
     * @param year2 second year to compare.
     * @return 
     */

    public double crimeIncrease ( String crimeDescription, int year1, int year2 ) {
        
        // WRITE YOUR CODE HERE

        double record_year1_count = 0;
        double record_year2_count = 0;
        double total1 = 0;
        double total2 = 0;
        
        if(year1 < year2)
        {
            for(SFYear SFyear_object1 : database)
            {
                if(SFyear_object1.getcurrentYear() == year1)
                {
                    for(SFRecord record : SFyear_object1.getRecordsForYear())
                    {
                        if(record.getDescription().indexOf(crimeDescription) != -1)
                        {
                            record_year1_count++;
                        }
                        total1++;
                    }
                }
            }

            for(SFYear SFyear_object2 : database)
            {
                if(SFyear_object2.getcurrentYear() == year2)
                {
                    for(SFRecord record : SFyear_object2.getRecordsForYear())
                    {
                        if(record.getDescription().indexOf(crimeDescription) != -1)
                        {
                            record_year2_count++;
                        }
                        total2++;
                    }
                }
            }
        }
        
        double increase_decrease_percentage = ((record_year2_count/total2) - (record_year1_count/total1))* 100;
	    return increase_decrease_percentage; // update the return value
    }

    /**
     * This method outputs the NYC borough where the most amount of stops 
     * occurred in a given year. This method will mainly analyze the five 
     * following boroughs in New York City: Brooklyn, Manhattan, Bronx, 
     * Queens, and Staten Island.
     * 
     * @param year we are only interested in the records of year.
     * @return the borough with the greatest number of stops
     */
    public String mostCommonBorough ( int year ) {

        // WRITE YOUR CODE HERE
        int[] counter_array = new int[5];
        int max = 0;
        int max_index = 0;
        String[] place = {"Brooklyn" , "Manhattan" , "Bronx" , "Queens" , "Staten Islands"};

        for(SFYear year_object : database)
        {
            if(year == year_object.getcurrentYear())
            {
                for(SFRecord record : year_object.getRecordsForYear())
                {
                    if(record.getLocation().equalsIgnoreCase("BROOKLYN"))
                    {
                        counter_array[0] = counter_array[0] + 1;
                    }
                    else if(record.getLocation().equalsIgnoreCase("MANHATTAN"))
                    {
                        counter_array[1] = counter_array[1] + 1;
                    }
                    else if(record.getLocation().equalsIgnoreCase("BRONX"))
                    {
                        counter_array[2] = counter_array[2] + 1;
                    }
                    else if(record.getLocation().equalsIgnoreCase("QUEENS"))
                    {
                        counter_array[3] = counter_array[3] + 1;
                    }
                    else if(record.getLocation().equalsIgnoreCase("STATEN ISLAND"))
                    {
                        counter_array[4] = counter_array[4] + 1;
                    }
                }
            }
        }

        for(int i=0 ; i<counter_array.length ; i++)
        {
            if(counter_array[i] > max)
            {
                max = counter_array[i];
                max_index += i;
            }
        }
        return place[max_index]; // update the return value
    }

}
