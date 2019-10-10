package seedu.address.storage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.Data;
import seedu.address.model.ReadOnlyData;
import seedu.address.model.person.Person;

/**
 * An Immutable Data that is serializable to JSON format.
 */
@JsonRootName(value = "addressbook")
class JsonSerializableAddressBook {

    public static final String MESSAGE_DUPLICATE_PERSON = "Persons list contains duplicate person(s).";

    private final List<JsonAdaptedPerson> persons = new ArrayList<>();

    /**
     * Constructs a {@code JsonSerializableAddressBook} with the given persons.
     */
    @JsonCreator
    public JsonSerializableAddressBook(@JsonProperty("persons") List<JsonAdaptedPerson> persons) {
        this.persons.addAll(persons);
    }

    /**
     * Converts a given {@code ReadOnlyData} into this class for Jackson use.
     *
     * @param source future changes to this will not affect the created {@code JsonSerializableAddressBook}.
     */
    public JsonSerializableAddressBook(ReadOnlyData<Person> source) {
        persons.addAll(source.getListOfElements().stream().map(JsonAdaptedPerson::new).collect(Collectors.toList()));
    }

    /**
     * Converts this address book into the model's {@code Data} object.
     *
     * @throws IllegalValueException if there were any data constraints violated.
     */
    public Data toModelType() throws IllegalValueException {
        Data<Person> persons = new Data();
        for (JsonAdaptedPerson jsonAdaptedPerson : this.persons) {
            Person person = jsonAdaptedPerson.toModelType();
            if (persons.hasUniqueElement(person)) {
                throw new IllegalValueException(MESSAGE_DUPLICATE_PERSON);
            }
            persons.addUniqueElement(person);
        }
        return persons;
    }

}
