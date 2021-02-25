package apps.math.vector;

import java.util.Arrays;
import java.util.stream.DoubleStream;

import apps.math.vector.exceptions.DifferentDimensionException;

/**
 * Класс описывающий математический вектор
 */
public class Vector implements Cloneable
{
	private double[] coordinates;
	
	/**
	 * Создание массива из векторов
	 *
	 * @param values Массив значений векторов
	 * @return Массив векторов
	 * @throws Exception Несоответствие count и dimension с содержимым vectors
	 */
	public static Vector[] createArray(double[][] values) throws Exception
	{
		Vector[] result = new Vector[values.length];
		
		for (int i = 0; i < values.length; i++)
		{
			result[i] = new Vector(values[i]);
		}
		
		return result;
	}
	
	/**
	 * Статическая версия сложения векторов
	 *
	 * @param first  Первый вектор
	 * @param second Второй вектор
	 * @return Новый вектор, являющийся сложением first и second
	 * @throws DifferentDimensionException Разная размерность векторов
	 */
	public static Vector add(Vector first, Vector second) throws DifferentDimensionException
	{
		if (first.coordinates.length != second.coordinates.length)
		{
			throw new DifferentDimensionException();
		}
		
		Vector result = new Vector(first.coordinates.length);
		
		for (int i = 0; i < first.coordinates.length; i++)
		{
			result.coordinates[i] = first.coordinates[i] + second.coordinates[i];
		}
		
		return result;
	}
	
	/**
	 * Статическая версия вычитания векторов
	 *
	 * @param first  Первый вектор
	 * @param second Второй вектор
	 * @return Новый вектор, являющийся вычитанием second из first
	 * @throws DifferentDimensionException Разная размерность векторов
	 */
	public static Vector subtract(Vector first, Vector second) throws DifferentDimensionException
	{
		if (first.coordinates.length != second.coordinates.length)
		{
			throw new DifferentDimensionException();
		}
		
		Vector result = new Vector(first.coordinates.length);
		
		for (int i = 0; i < first.coordinates.length; i++)
		{
			result.coordinates[i] = first.coordinates[i] - second.coordinates[i];
		}
		
		return result;
	}
	
	/**
	 * Статическая версия умножения вектора на число
	 *
	 * @param vector   Вектор, координаты которого будут умножаться на константу
	 * @param constant Константа, которая будет умножаться на координаты вектора
	 * @return Новый вектор, являющийся умножением vector на constant
	 */
	public static Vector multiply(Vector vector, double constant)
	{
		Vector result = new Vector(vector);
		
		for (int i = 0; i < result.coordinates.length; i++)
		{
			result.coordinates[i] *= constant;
		}
		
		return result;
	}
	
	/**
	 * Конструктор для задания координат вектора
	 *
	 * @param coordinates Координаты вектора
	 */
	public Vector(double... coordinates)
	{
		this.coordinates = Arrays.copyOf(coordinates, coordinates.length);
	}
	
	/**
	 * Копирующий конструктор
	 *
	 * @param other Вектор для копирования
	 */
	public Vector(Vector other)
	{
		coordinates = Arrays.copyOf(other.coordinates, other.coordinates.length);
	}
	
	/**
	 * Конструктор для задания размерности вектора
	 *
	 * @param dimension Размерность вектора
	 */
	public Vector(int dimension)
	{
		coordinates = new double[dimension];
	}
	
	/**
	 * Вычисление модуля вектора
	 *
	 * @return Модуль вектора
	 */
	public double calculateModule()
	{
		double result = 0.0;
		
		for (double coordinate : coordinates)
		{
			result += coordinate * coordinate;
		}
		
		return Math.sqrt(result);
	}
	
	/**
	 * Вычисления скалярного произведения векторов
	 *
	 * @param other Второй вектор в скалярном произведении
	 * @return Результат скалярного произведения
	 * @throws DifferentDimensionException Разная размерность векторов
	 */
	public double calculateScalarProduct(Vector other) throws DifferentDimensionException
	{
		if (coordinates.length != other.coordinates.length)
		{
			throw new DifferentDimensionException();
		}
		
		double result = 0.0;
		
		for (int i = 0; i < coordinates.length; i++)
		{
			result += coordinates[i] * other.coordinates[i];
		}
		
		return result;
	}
	
	/**
	 * Сложение вектора с текущим вектором
	 *
	 * @param other Второй вектор в сложении
	 * @return Ссылку на себя после сложения
	 * @throws DifferentDimensionException Разная размерность векторов
	 */
	public Vector add(Vector other) throws DifferentDimensionException
	{
		if (coordinates.length != other.coordinates.length)
		{
			throw new DifferentDimensionException();
		}
		
		for (int i = 0; i < coordinates.length; i++)
		{
			coordinates[i] += other.coordinates[i];
		}
		
		return this;
	}
	
	/**
	 * Вычитание из текущего вектора другого
	 *
	 * @param other Вычитаемый вектор
	 * @return Ссылку на себя после вычитания
	 * @throws DifferentDimensionException Разная размерность векторов
	 */
	public Vector subtract(Vector other) throws DifferentDimensionException
	{
		if (coordinates.length != other.coordinates.length)
		{
			throw new DifferentDimensionException();
		}
		
		for (int i = 0; i < coordinates.length; i++)
		{
			coordinates[i] -= other.coordinates[i];
		}
		
		return this;
	}
	
	/**
	 * Умножение текущего вектора на константу
	 *
	 * @param constant Константа для умножения на вектор
	 * @return Ссылку на себя после умножения на константу
	 */
	public Vector multiply(double constant)
	{
		for (int i = 0; i < coordinates.length; i++)
		{
			coordinates[i] *= constant;
		}
		
		return this;
	}
	
	/**
	 * Проверка векторов на ортагональность
	 *
	 * @param other Второй вектор
	 * @return true в случае ортогональности векторов, false иначе
	 * @throws DifferentDimensionException Разная размерность векторов
	 */
	public boolean isOrthogonal(Vector other) throws DifferentDimensionException
	{
		return this.calculateScalarProduct(other) == 0.0;
	}
	
	/**
	 * Проверка векторов на коллинеарность
	 *
	 * @param other Второй вектор
	 * @return true в случае коллинеарности векторов, false иначе
	 * @throws DifferentDimensionException Разная размерность векторов
	 */
	public boolean isCollinear(Vector other) throws DifferentDimensionException
	{
		if (coordinates.length != other.coordinates.length)
		{
			throw new DifferentDimensionException();
		}
		
		if (DoubleStream.of(coordinates).anyMatch(value -> value == 0.0) || DoubleStream.of(other.coordinates).anyMatch(value -> value == 0.0))
		{
			double ratio = 0.0;
			boolean check = false;
			
			for (int i = 0; i < coordinates.length; i++)
			{
				if (coordinates[i] != 0.0 && other.coordinates[i] != 0.0)
				{
					ratio = coordinates[i] / other.coordinates[i];
					
					break;
				}
			}
			
			for (int i = 0; i < coordinates.length; i++)
			{
				if (coordinates[i] != 0.0 && other.coordinates[i] != 0.0)
				{
					check = true;
					
					if (coordinates[i] / other.coordinates[i] != ratio)
					{
						check = false;
						
						break;
					}
				}
			}
			
			return check;
		}
		else
		{
			double start = coordinates[0] / other.coordinates[0];
			
			for (int i = 1; i < coordinates.length; i++)
			{
				if (coordinates[i] / other.coordinates[i] != start)
				{
					return false;
				}
			}
		}
		
		return true;
	}
	
	/**
	 * Setter для coordinates
	 *
	 * @param coordinates Новые координаты вектора
	 */
	public void setCoordinates(double[] coordinates)
	{
		this.coordinates = coordinates;
	}
	
	/**
	 * Getter для coordinates
	 *
	 * @return coordinates
	 */
	public double[] getCoordinates()
	{
		return coordinates;
	}
	
	/**
	 * Клонирование вектора
	 *
	 * @return Новый вектор идентичный данному
	 */
	@Override
	public Vector clone()
	{
		try
		{
			return (Vector) super.clone();
		}
		catch (CloneNotSupportedException e)
		{
			e.printStackTrace();
			
			return null;
		}
	}
	
	/**
	 * Представление вектора в виде строки
	 *
	 * @return Вектор в формате (a1, a2, ..., an), где a1, a2, ..., an - координаты вектора
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append('(');
		
		for (int i = 0; i < coordinates.length; i++)
		{
			builder.append(coordinates[i]);
			
			if (i + 1 != coordinates.length)
			{
				builder.append(", ");
			}
		}
		
		builder.append(')');
		
		return builder.toString();
	}
	
	/**
	 * Сравнение векторов
	 *
	 * @param vector Вектор для сравнения
	 * @return true в случае равенства всех координат векторов, false иначе
	 */
	@Override
	public boolean equals(Object vector)
	{
		if (getClass() != vector.getClass())
		{
			return false;
		}
		
		Vector ref = (Vector) vector;
		
		if (coordinates.length != ref.coordinates.length)
		{
			return false;
		}
		
		for (int i = 0; i < coordinates.length; i++)
		{
			if (coordinates[i] != ref.coordinates[i])
			{
				return false;
			}
		}
		
		return true;
	}
}
