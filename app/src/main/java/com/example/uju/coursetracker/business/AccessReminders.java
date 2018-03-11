package com.example.uju.coursetracker.business;


import com.example.uju.coursetracker.application.DatabaseService;
import com.example.uju.coursetracker.presentation.MainActivity;
import com.example.uju.coursetracker.objects.Reminder;
import com.example.uju.coursetracker.persistence.DataAccess;
import java.util.List;


public class AccessReminders
{
    private DataAccess dataAccess;
    private List<Reminder> reminderList;

    public AccessReminders()
    {
        dataAccess = DatabaseService.getDataAccess(MainActivity.dbName);
        reminderList = null;
    }


    /////////////////////////////
    //Current Courses Reminders:
    /////////////////////////////

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

}
