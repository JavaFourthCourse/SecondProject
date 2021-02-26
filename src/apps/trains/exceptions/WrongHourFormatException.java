package apps.trains.exceptions;

/**
 * Класс описывающий исключение связанное с неверным форматом часов
 */
public class WrongHourFormatException extends Exception
{
	/**
	 * Конструктор по умолчанию, создающий нужное сообщение для Exception
	 */
	public WrongHourFormatException()
	{
		super("Неверный формат часов\nДопустимым является формат 0-23");
	}
}
