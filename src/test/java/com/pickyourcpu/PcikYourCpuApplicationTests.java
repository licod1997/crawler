package com.pickyourcpu;

import com.pickyourcpu.repository.ProductRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PcikYourCpuApplicationTests {

	@Autowired
	private ProductRepository productRepository;

	@Test
	public void contextLoads() {
		System.out.println(productRepository.findDistinctNoOfCores());
		System.out.println(productRepository.findDistinctSocket());
	}

}
