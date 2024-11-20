public class AppointmentBook{


    private boolean[][] schedule;

    public AppointmentBook() 
    {
        schedule = new boolean[8][60];
        for (int i = 0; i < 8; i++) 
        {
            for (int j = 0; j < 60; j++) 
            {
                schedule[i][j] = true;
            }
        }
    }


    private boolean isMinuteFree(int period, int minute) 
    {
        return schedule[period - 1][minute];
    }

    private void reserveBlock(int period, int startMinute, int duration) 
    {
        for (int i = startMinute; i < startMinute + duration; i++) 
        {
            schedule[period - 1][i] = false;
        }
    }


    public int findFreeBlock(int period, int duration) 
    {
        int freeBlockCount = 0;

        for (int minute = 0; minute < 60; minute++) 
        {
            if (isMinuteFree(period, minute)) 
            {
                freeBlockCount++;
                if (freeBlockCount == duration) 
                {
                    return minute - duration + 1;
                }
            } else {
                freeBlockCount = 0;
            }
        }
        return -1;
    }

    public boolean makeAppointment(int startPeriod, int endPeriod, int duration) 
    {
        for (int period = startPeriod; period <= endPeriod; period++) 
        {
            int startMinute = findFreeBlock(period, duration);
            if (startMinute != -1) 
            {
                reserveBlock(period, startMinute, duration);
                return true;
            }
        }

        return false;
    }

    public void printSchedule() 
    {
        for (int i = 0; i < 8; i++) 
        {
            System.out.print("Period " + (i + 1) + ": ");
            for (int j = 0; j < 60; j++) 
            {
                System.out.print(schedule[i][j] ? "." : "X");
            }

            System.out.println();
        }
    }

    public static void main(String[] args) {
        AppointmentBook book = new AppointmentBook();

        // Test cases
        System.out.println("Initial Schedule:");
        book.printSchedule();

        System.out.println("\nMaking appointment for 15 minutes in period 1...");
        book.makeAppointment(1, 1, 15);
        book.printSchedule();

        System.out.println("\nMaking another appointment for 10 minutes in periods 1-2...");
        book.makeAppointment(1, 2, 10);
        book.printSchedule();

        System.out.println("\nTrying to find a free block of 30 minutes in period 2...");
        int startMinute = book.findFreeBlock(2, 30);
        System.out.println("Start minute for free block in period 2: " + startMinute);
    }
    
}
