package apps.math.vector.exceptions;

/**
 * Класс описывающий исключение связанное с размерностью векторов
 */
public class DifferentDimensionException extends Exception
{
	/**
	 * Конструктор по умолчанию, создающий нужное сообщение для Exception
	 */
	public DifferentDimensionException()
	{
		super("Разная размерность у векторов");
	}
}
