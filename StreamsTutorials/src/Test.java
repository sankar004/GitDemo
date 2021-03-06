import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.testng.Assert;


public class Test {
	
	@org.testng.annotations.Test
	public void streamFilter() {
		
		ArrayList<String> names =new ArrayList<String>();
		names.add("Muthu");
		names.add("Abijit");
		names.add("Sankar");
		names.add("Appadurai");
		names.add("Anu");
		
		ArrayList<String> names1 =new ArrayList<String>();
		names.add("Dhoni");
		names.add("Raina");
		names.add("jaddu");
		
		System.out.println(names.stream().filter(s -> s.startsWith("A")).count());
		long d = names.stream().filter(s->
		{
			s.startsWith("A");
			return true;
		}
		).count();
		System.out.println(d);	
		
		names.stream().filter(s -> s.length()>4).forEach(s -> System.out.println(s)); 
		names.stream().filter(s -> s.length()>4).limit(1).forEach(s -> System.out.println(s));
		
		names.stream().filter(s -> s.endsWith("u")).map(s -> s.toUpperCase()).forEach(s -> System.out.println(s));
		names.stream().filter(s -> s.startsWith("A")).sorted().map(s -> s.toUpperCase()).forEach(s -> System.out.println(s));
		
		Stream<String> newStream = Stream.concat(names.stream(), names1.stream());
		//newStream.forEach(s -> System.out.println(s));
		
		boolean flag = newStream.anyMatch(s->s.equalsIgnoreCase("muthu"));
		
		Assert.assertTrue(flag);
		
		List<String> ls =names.stream().filter(s -> s.startsWith("A")).collect(Collectors.toList());
		System.out.println(ls.get(0));
		
		List<Integer> num = Arrays.asList(2,5,3,2,5,3,2,2,6,8,9,4,8,6);
		
		num.stream().distinct().sorted().forEach(s -> System.out.println(s));
		
		
	}
}
