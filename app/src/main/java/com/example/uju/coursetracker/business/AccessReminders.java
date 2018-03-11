package com.example.uju.coursetracker.business;

import com.example.uju.coursetracker.application.DatabaseService;
import com.example.uju.coursetracker.presentation.MainActivity;
import com.example.uju.coursetracker.objects.Reminder;
import com.example.uju.coursetracker.persistence.DataAccess;
import java.util.ArrayList;
import java.util.List;


public class AccessReminders
{
    private DataAccess dataAccess;
    private List<Reminder> reminderList;
    private Reminder reminder;
    private int count;

    public AccessReminders()
    {
        dataAccess = DatabaseService.getDataAccess(MainActivity.dbName);
        reminderList = null;
        reminder = null;
        count = 0 ;
    }


    public String getRemindersSeq(List<Reminder> reminders)
    {
        reminders.clear();
        return dataAccess.getRemindersSeq(reminders);
    }

    public String insertReminder(Reminder reminder)
    {
        return dataAccess.insertReminder(reminder);
    }

    public String updateReminder(Reminder reminder)
    {
        return dataAccess.updateReminder(reminder);
    }

    public String deleteReminder(Reminder reminder)
    {
        return dataAccess.deleteReminder(reminder);
    }


    public Reminder getSequentialReminders()
    {
        String result = null;
        if (reminderList == null)
        {
            // the following line was added as a result of a failing test in AccessRemindersTest!
            reminderList = new ArrayList<Reminder>();
            result = dataAccess.getRemindersSeq(reminderList);
            count = 0;
        }
        if (count < reminderList.size())
        {
            reminder = reminderList.get(count);
            count++;
        }
        else
        {
            reminderList = null;
            reminder = null;
            count = 0;
        }
        return reminder;
    }


}
