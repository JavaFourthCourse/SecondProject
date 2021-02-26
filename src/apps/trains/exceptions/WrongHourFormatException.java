package apps.trains.exceptions;

public class WrongHourFormatException extends Exception
{
	public WrongHourFormatException()
	{
		super("Неверный формат часов\nДопустимым является формат 0-23");
	}
}
