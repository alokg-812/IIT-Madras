class Time:
    def __init__(self, time):
        self.time = time
        
    def seconds_to_minutes(self):
        minutes = self.time //60
        seconds = self.time % 60
        return f"{minutes} min {seconds} sec"
        
    def seconds_to_hours(self):
        hours = self.time //3600
        minutes = (self.time %3600) //60
        seconds = self.time % 60
        return f"{hours} hrs {minutes} min {seconds} sec"
        
    def seconds_to_days(self):
        days = self.time //86400
        hours = (self.time % 86400) //3600
        minutes = ((self.time % 86400) % 3600) //60
        seconds = self.time % 60
        return f"{days} days {hours} hrs {minutes} min {seconds} sec"
