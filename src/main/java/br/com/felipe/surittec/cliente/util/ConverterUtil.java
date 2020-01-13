package br.com.felipe.surittec.cliente.util;

import br.com.felipe.surittec.cliente.exception.ClienteException;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;

public class ConverterUtil {

	private ConverterUtil() {}

	private static final Logger logger = LoggerFactory.getLogger(ConverterUtil.class);

	private static final ModelMapper modelMapper = new ModelMapper();

	public static <T> T converterToDTO(Object source, Class<T> target, String ... ignoreProperties) {
		try {
			T result = target.newInstance();
			BeanUtils.copyProperties(source, result, ignoreProperties);
			return result;
		} catch (InstantiationException | IllegalAccessException e) {
			logger.error(e.getMessage());
			throw new ClienteException("erro.converter");
		}
	}

	public static <T> T converterToDTO(Object source, Class<T> target) {
		return converterToDTO(source, target, "");
	}

	public static <T> T deepConvertToDTO(Object source, Class<T> target) {
		return modelMapper.map(source, target);
	}
}
