package org.tux.config;

import java.util.Random;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.tux.entites.Personne;

public class PersonItemProcessor implements ItemProcessor<Personne, Personne>{
	
	private static final Logger log=LoggerFactory.getLogger(PersonItemProcessor.class);

	
	//method to edit the name of a personne to the UPERCASE
	@Override
	public Personne process(final Personne personne) throws Exception {
		
		final String firstName = personne.getPrenom();
		final String lastName =personne.getNom().toUpperCase();
		
		log.info("processing converting ("+ personne.getNom() +" ) TO (" + lastName + ")");

		final Personne transformerPerson = new Personne(personne.getId(),firstName,lastName);
		
		return transformerPerson;
	}

}
