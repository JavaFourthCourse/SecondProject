package apps.trains;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import apps.trains.exceptions.WrongHourFormatException;
import apps.trains.exceptions.WrongSeatsTypeException;

/**
 * Класс описывающий поезд
 */
public class Train
{
	/**
	 * Описание типов мест в поезде
	 */
	public enum SeatType
	{
		/**
		 * Общие места
		 */
		COMMON,
		/**
		 * Места купе
		 */
		COMPARTMENT,
		/**
		 * Места люкс
		 */
		LUXURY
	}
	
	private String destination;
	private String number;
	private Calendar departureTime;
	private List<Integer> seats;
	
	/**
	 * Создание массива поездов
	 *
	 * @param destinations     Пункты назначения
	 * @param numbers          Номера поездов
	 * @param departureTimes   Время прибытия поездов
	 * @param commonSeats      Количество общих мест в поездах
	 * @param compartmentSeats Количество мест купе в поездах
	 * @param luxurySeats      Количетсво мест люкс в поездах
	 * @return Массив поездов
	 * @throws Exception Разные размеры массивов
	 */
	public static Train[] createArray(String[] destinations, String[] numbers, Calendar[] departureTimes, int[] commonSeats, int[] compartmentSeats, int[] luxurySeats) throws Exception
	{
		int[] lengths = new int[6];
		
		lengths[0] = destinations.length;
		lengths[1] = numbers.length;
		lengths[2] = departureTimes.length;
		lengths[3] = commonSeats.length;
		lengths[4] = compartmentSeats.length;
		lengths[5] = luxurySeats.length;
		
		if (!Arrays.stream(lengths).allMatch(length -> length == lengths[0]))
		{
			throw new Exception("Размеры массивов должны быть одинаковы");
		}
		
		Train[] result = new Train[destinations.length];
		
		for (int i = 0; i < lengths[0]; i++)
		{
			result[i] = new Train(destinations[i], numbers[i], departureTimes[i], commonSeats[i], compartmentSeats[i], luxurySeats[i]);
		}
		
		return result;
	}
	
	/**
	 * Список поездов, следующих до заданного пункта назначения
	 *
	 * @param trains      Список поездов
	 * @param destination Пункт назначения
	 * @return Список поездов, следующих до destination
	 */
	public static List<Train> getTrainListByDestination(List<Train> trains, String destination)
	{
		return trains.stream().filter(train -> train.getDestination().equals(destination)).collect(Collectors.toList());
	}
	
	/**
	 * Список поездов, следующих до заданного пункта назначения и отправляющихся после заданного часа
	 *
	 * @param trains      Список поездов
	 * @param destination Пункт назначения
	 * @param hour        Время для сравнения
	 * @return Список поездов, следующих до destination после hour
	 * @throws WrongHourFormatException Неверный формат hour
	 */
	public static List<Train> getTrainListByDestinationAndAfterHour(List<Train> trains, String destination, int hour) throws WrongHourFormatException
	{
		if (hour >= 0 && hour <= 23)
		{
			return trains.stream().filter
					(
							train ->
									train.getDestination().equals(destination) &&
											train.getDepartureTime().get(Calendar.HOUR_OF_DAY) > hour
					).collect(Collectors.toList());
		}
		else
		{
			throw new WrongHourFormatException();
		}
	}
	
	/**
	 * Список поездов, отправляющихся до заданного пункта назначения и имеющих общие места
	 *
	 * @param trains      Список поездов
	 * @param destination Пункт назначения
	 * @return Список поездов, следующих до destination и seats.get(SeatType.COMMON.ordinal()) != 0
	 */
	public static List<Train> getTrainListByDestinationAndCommonSeatType(List<Train> trains, String destination)
	{
		return trains.stream().filter
				(
						train ->
								train.getDestination().equals(destination) &&
										train.getSeats().get(SeatType.COMMON.ordinal()) != 0
				).collect(Collectors.toList());
	}
	
	/**
	 * Конструктор отправляющегося поезда
	 *
	 * @param destination      Пункт назначения
	 * @param number           Номер поезда
	 * @param departureTime    Время прибытия
	 * @param commonSeats      Количество общих мест
	 * @param compartmentSeats Количество мест купе
	 * @param luxurySeats      Количество мест люкс
	 */
	public Train(String destination, String number, Calendar departureTime, int commonSeats, int compartmentSeats, int luxurySeats)
	{
		this.destination = destination;
		this.number = number;
		this.departureTime = (Calendar) departureTime.clone();
		
		seats = new ArrayList<>(SeatType.values().length);
		
		seats.add(commonSeats);
		seats.add(compartmentSeats);
		seats.add(luxurySeats);
	}
	
	/**
	 * Конструктор поезда
	 *
	 * @param number           Номер поезда
	 * @param commonSeats      Количество общих мест
	 * @param compartmentSeats Количество мест купе
	 * @param luxurySeats      Количество мест люкс
	 */
	public Train(String number, int commonSeats, int compartmentSeats, int luxurySeats)
	{
		this.number = number;
		
		seats = new ArrayList<>(SeatType.values().length);
		
		seats.add(commonSeats);
		seats.add(compartmentSeats);
		seats.add(luxurySeats);
	}
	
	/**
	 * Setter для departureTime
	 *
	 * @param departureTime Новое значение departureTime
	 */
	public void setDepartureTime(Calendar departureTime)
	{
		this.departureTime = (Calendar) departureTime.clone();
	}
	
	/**
	 * Setter для destination
	 *
	 * @param destination Новое значение destination
	 */
	public void setDestination(String destination)
	{
		this.destination = destination;
	}
	
	/**
	 * Setter для number
	 *
	 * @param number Новое значение number
	 */
	public void setNumber(String number)
	{
		this.number = number;
	}
	
	/**
	 * Setter для seats
	 *
	 * @param seats Новое значение seats
	 * @throws WrongSeatsTypeException Неверный формат посадочных мест
	 */
	public void setSeats(List<Integer> seats) throws WrongSeatsTypeException
	{
		if (seats.size() != SeatType.values().length)
		{
			throw new WrongSeatsTypeException();
		}
		
		this.seats = new ArrayList<>(SeatType.values().length);
		
		this.seats.addAll(seats);
	}
	
	/**
	 * Getter для seats<br>
	 * List - ArrayList
	 *
	 * @return seats
	 */
	public List<Integer> getSeats()
	{
		return seats;
	}
	
	/**
	 * Getter для departureTime
	 *
	 * @return departureTime
	 */
	public Calendar getDepartureTime()
	{
		return departureTime;
	}
	
	/**
	 * Getter для destination
	 *
	 * @return destination
	 */
	public String getDestination()
	{
		return destination;
	}
	
	/**
	 * Getter для number
	 *
	 * @return number
	 */
	public String getNumber()
	{
		return number;
	}
	
	/**
	 * Вся информация о поезде
	 *
	 * @return Информация о поезде в формате:<br>
	 * number<br>
	 * destination: departureTime <br>
	 * common: numberOfCommon <br>
	 * compartment: numberOfCompartment <br>
	 * luxury: numberOfLuxury
	 */
	@Override
	public String toString()
	{
		StringBuilder builder = new StringBuilder();
		SeatType[] seatsType = SeatType.values();
		
		builder.append(number).append('\n').
				append(destination).append(": ").append(departureTime.getTime()).append('\n');
		
		for (int i = 0; i < seats.size(); i++)
		{
			builder.append(seatsType[i].toString().toLowerCase()).append(": ").append(seats.get(i));
			
			if (i + 1 != seats.size())
			{
				builder.append('\n');
			}
		}
		
		return builder.toString();
	}
}
