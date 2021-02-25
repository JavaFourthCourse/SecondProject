package apps.math.set;

import java.util.ArrayList;
import java.util.List;

/**
 * Класс описывающий множество символов
 */
public class CharSet implements Cloneable
{
	private List<Character> chars;
	
	public static CharSet[] createArray(char[][] elements)
	{
		CharSet[] result = new CharSet[elements.length];
		
		for (int i = 0; i < elements.length; i++)
		{
			result[i] = new CharSet(elements[i]);
		}
		
		return result;
	}
	
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
	
	public CharSet(char... chars)
	{
		this.chars = new ArrayList<>();
		
		for (char character : chars)
		{
			this.add(character);
		}
	}
	
	public CharSet(CharSet other)
	{
		chars = new ArrayList<>();
		
		chars.addAll(other.chars);
	}
	
	public CharSet(List<Character> chars)
	{
		this.chars = new ArrayList<>();
		
		for (Character character : chars)
		{
			this.add(character);
		}
	}
	
	public int cardinality()
	{
		return chars.size();
	}
	
	public boolean contains(char element)
	{
		return chars.contains(element);
	}
	
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
	
	public CharSet getUnion(CharSet other)
	{
		CharSet result = new CharSet(chars);
		
		for (Character character : other.chars)
		{
			result.add(character);
		}
		
		return result;
	}
	
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
	
	public void add(char element)
	{
		if (!this.contains(element))
		{
			chars.add(element);
		}
	}
	
	public void add(CharSet other)
	{
		chars = this.getUnion(other).chars;
	}
	
	public void subtract(CharSet other)
	{
		chars = this.getDifference(other).chars;
	}
	
	public void multiply(CharSet other)
	{
		chars = this.getIntersection(other).chars;
	}
	
	public char get(int index) throws IndexOutOfBoundsException
	{
		return chars.get(index);
	}
	
	public void set(int index, char element) throws IndexOutOfBoundsException
	{
		chars.set(index, element);
	}
	
	public CharSet append(char element)
	{
		this.add(element);
		
		return this;
	}
	
	public void setChars(List<Character> chars)
	{
		this.chars = chars;
	}
	
	public List<Character> getChars()
	{
		return chars;
	}
	
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
