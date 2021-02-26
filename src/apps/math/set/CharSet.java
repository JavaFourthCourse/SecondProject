package apps.math.set;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Класс описывающий множество символов
 */
public class CharSet implements Cloneable
{
	private List<Character> chars;
	
	/**
	 * Создание массива множеств символов
	 *
	 * @param elements Элементы множеств символов
	 * @return Массив множеств символов
	 */
	public static CharSet[] createArray(char[][] elements)
	{
		CharSet[] result = new CharSet[elements.length];
		
		for (int i = 0; i < elements.length; i++)
		{
			result[i] = new CharSet(elements[i]);
		}
		
		return result;
	}
	
	/**
	 * Создание множества из символов, которые входят только в одно множество
	 *
	 * @param first  Первое множество
	 * @param second Второе множество
	 * @return Множество в котором находятся элементы только из одного множества
	 */
	public static CharSet construct(CharSet first, CharSet second)
	{
		CharSet result = new CharSet();
		
		for (Character character : first.chars)
		{
			if (!second.contains(character))
			{
				result.add(character);
			}
		}
		
		for (Character character : second.chars)
		{
			if (!first.contains(character))
			{
				result.add(character);
			}
		}
		
		return result;
	}
	
	/**
	 * Конструктор для перечисления элементов
	 *
	 * @param chars Элементы множества
	 */
	public CharSet(char... chars)
	{
		this.chars = new ArrayList<>();
		
		for (char character : chars)
		{
			this.add(character);
		}
	}
	
	/**
	 * Копирующий конструктор
	 *
	 * @param other Другое множество
	 */
	public CharSet(CharSet other)
	{
		chars = new ArrayList<>();
		
		chars.addAll(other.chars);
	}
	
	/**
	 * Создание множества из List
	 *
	 * @param chars Элементы множества
	 */
	public CharSet(List<Character> chars)
	{
		this.chars = new ArrayList<>();
		
		for (Character character : chars)
		{
			this.add(character);
		}
	}
	
	/**
	 * Мощность множества
	 *
	 * @return Мощность множества
	 */
	public int cardinality()
	{
		return chars.size();
	}
	
	/**
	 * Проверка на наличие элемента в множестве
	 *
	 * @param element Проверяемый элемент
	 * @return true в случае присутствия элемента в множестве, false иначе
	 */
	public boolean contains(char element)
	{
		return chars.contains(element);
	}
	
	/**
	 * Пересечение множеств
	 *
	 * @param other Другое множество
	 * @return Множество содержащее элементы, которые одновременно принадлежат обоим множествам
	 */
	public CharSet getIntersection(CharSet other)
	{
		CharSet result = new CharSet();
		
		for (Character character : chars)
		{
			if (this.contains(character) && other.contains(character))
			{
				result.add(character);
			}
		}
		
		return result;
	}
	
	/**
	 * Объединения множеств
	 *
	 * @param other Другое множество
	 * @return Множество, содержащее элементы обоих множеств
	 */
	public CharSet getUnion(CharSet other)
	{
		CharSet result = new CharSet(chars);
		
		for (Character character : other.chars)
		{
			result.add(character);
		}
		
		return result;
	}
	
	/**
	 * Разность множеств
	 *
	 * @param other Другое множество
	 * @return Множество, содержащие элементы из текущего множества, которые не содержатся в other
	 */
	public CharSet getDifference(CharSet other)
	{
		CharSet result = new CharSet();
		
		for (Character character : chars)
		{
			if (!other.contains(character))
			{
				result.add(character);
			}
		}
		
		return result;
	}
	
	/**
	 * Добавление элемента в множество<br>
	 * В множестве один элемент может содержаться только 1 раз
	 *
	 * @param element Элемент для добавления
	 */
	public void add(char element)
	{
		if (!this.contains(element))
		{
			chars.add(element);
		}
	}
	
	/**
	 * Сложение множеств
	 *
	 * @param other Другое множество
	 */
	public void add(CharSet other)
	{
		chars = this.getUnion(other).chars;
	}
	
	/**
	 * Вычитание множеств
	 *
	 * @param other Другое множество
	 */
	public void subtract(CharSet other)
	{
		chars = this.getDifference(other).chars;
	}
	
	/**
	 * Умножение множеств
	 *
	 * @param other Другое множество
	 */
	public void multiply(CharSet other)
	{
		chars = this.getIntersection(other).chars;
	}
	
	/**
	 * Получение элемента множества по индексу
	 *
	 * @param index Индекс элемента в множестве
	 * @return Элемент множества
	 * @throws IndexOutOfBoundsException Индекс >= мощности множества
	 */
	public char get(int index) throws IndexOutOfBoundsException
	{
		return chars.get(index);
	}
	
	/**
	 * Переприсваивание элемента множества по индексу
	 *
	 * @param index   Индекс элемента в множестве для замены
	 * @param element Новый элемент множества
	 * @throws IndexOutOfBoundsException Индекс >= мощности множества
	 */
	public void set(int index, char element) throws IndexOutOfBoundsException
	{
		chars.set(index, element);
		
		chars = chars.stream().distinct().collect(Collectors.toList());
	}
	
	/**
	 * Добавление элемента в множество
	 *
	 * @param element Элемент для добавления
	 * @return Текущее множество
	 */
	public CharSet append(char element)
	{
		this.add(element);
		
		return this;
	}
	
	/**
	 * Setter для chars
	 *
	 * @param chars Новое значение chars
	 */
	public void setChars(List<Character> chars)
	{
		this.chars = chars;
	}
	
	/**
	 * Getter для chars
	 *
	 * @return chars
	 */
	public List<Character> getChars()
	{
		return chars;
	}
	
	/**
	 * Клонирование множества
	 *
	 * @return Новое множество идентичное данному
	 */
	@Override
	public CharSet clone()
	{
		try
		{
			return (CharSet) super.clone();
		}
		catch (CloneNotSupportedException supportedException)
		{
			supportedException.printStackTrace();
			
			return null;
		}
	}
	
	/**
	 * Представление множества в виде строки
	 *
	 * @return Множество в формате {a1, a2, ..., an}, где a1, a2, ..., an - элементы множества
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		
		builder.append('{');
		
		for (int i = 0; i < chars.size(); i++)
		{
			builder.append(chars.get(i));
			
			if (i + 1 != chars.size())
			{
				builder.append(", ");
			}
		}
		
		builder.append('}');
		
		return builder.toString();
	}
	
	/**
	 * Сравнение множеств
	 *
	 * @param other Другое множество для сравнения
	 * @return true в случае равенства множеств, false иначе
	 */
	@Override
	public boolean equals(Object other)
	{
		if (getClass() != other.getClass())
		{
			return false;
		}
		
		CharSet ref = (CharSet) other;
		
		if (this.cardinality() != ref.cardinality())
		{
			return false;
		}
		
		return this.cardinality() == this.getUnion(ref).cardinality();
	}
}
