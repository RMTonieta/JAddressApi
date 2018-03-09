package whs.jaddressapi.exceptions;

import org.springframework.http.HttpStatus;

import dsk.restUtilities.exceptions.ARestException;

public class AddressNotFoundException extends ARestException{

	@Override
	public String getUserMessage() {
		return "Endereço não encontrado";
	}

	@Override
	public HttpStatus getHttpStatus() {
		return HttpStatus.NOT_FOUND;
	}

}
