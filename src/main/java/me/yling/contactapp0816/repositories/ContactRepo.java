package me.yling.contactapp0816.repositories;

import me.yling.contactapp0816.models.Contact;
import org.springframework.data.repository.CrudRepository;

public interface ContactRepo extends CrudRepository <Contact, Long> {
}
