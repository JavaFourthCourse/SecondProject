package apps.trains.exceptions;

/**
 * Класс описывающий исключение связанное с неверным форматом посадочных мест
 */
public class WrongSeatsTypeException extends Exception
{
	/**
	 * Конструктор по умолчанию, создающий нужное сообщение для Exception
	 */
 	public WrongSeatsTypeException()
	{
		super("Неверный формат посадочных мест");
	}
}
