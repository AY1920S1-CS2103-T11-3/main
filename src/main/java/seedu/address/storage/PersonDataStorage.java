package seedu.address.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.address.commons.exceptions.DataConversionException;
import seedu.address.model.PersonData;
import seedu.address.model.ReadOnlyPersonData;

/**
 * Represents a storage for {@link PersonData}.
 */
public interface PersonDataStorage {

    /**
     * Returns the file path of the data file.
     */
    Path getPersonDataFilePath();

    /**
     * Returns PersonData data as a {@link ReadOnlyPersonData}.
     *   Returns {@code Optional.empty()} if storage file is not found.
     * @throws DataConversionException if the data in storage is not in the expected format.
     * @throws IOException if there was any problem when reading from the storage.
     */
    Optional<ReadOnlyPersonData> readPersonData() throws DataConversionException, IOException;

    /**
     * @see #getPersonDataFilePath()
     */
    Optional<ReadOnlyPersonData> readPersonData(Path filePath) throws DataConversionException, IOException;

    /**
     * Saves the given {@link ReadOnlyPersonData} to the storage.
     * @param addressBook cannot be null.
     * @throws IOException if there was any problem writing to the file.
     */
    void saveAddressBook(ReadOnlyPersonData addressBook) throws IOException;

    /**
     * @see #saveAddressBook(ReadOnlyPersonData)
     */
    void saveAddressBook(ReadOnlyPersonData addressBook, Path filePath) throws IOException;

}
