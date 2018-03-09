package whs.jaddressapi.correios;

import java.io.IOException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dsk.restUtilities.utils.RestExceptionUtils;
import whs.jaddressapi.base.Address;

@CrossOrigin
@RestController
@RequestMapping("/address")
public class AddressController {

	@RequestMapping(value="/{endereco}", method=RequestMethod.GET, produces="application/json; charset=UTF-8")
	public ResponseEntity<Object> getAddressInformation(@PathVariable String endereco, HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		try {

			Address returnedAddress = new CorreiosMobileCrawler().getAddress(endereco);
			
			return new ResponseEntity<Object>(returnedAddress, HttpStatus.OK);
	
		} catch (Exception e) {
			return RestExceptionUtils.getErrorResponseObject(e);
		}
		
	}
	
}
