package seedu.address.logic.commands;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Lesson;
import seedu.address.model.student.Student;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

class DeleteLessonCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_validIndex_success() throws CommandException {
        Student studentToEdit = model.getFilteredStudentList().get(0); // assuming 0 is a valid index
        Lesson lessonToDelete = studentToEdit.getObservableList().get(0); // assuming 0 is a valid lesson index
        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(Index.fromZeroBased(0), Index.fromZeroBased(0));

        CommandResult commandResult = deleteLessonCommand.execute(model);

        assertEquals(String.format(DeleteLessonCommand.MESSAGE_DELETE_LESSON_SUCCESS, studentToEdit.getName()),
                commandResult.getFeedbackToUser());
    }

    @Test
    public void execute_invalidStudentIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(outOfBoundIndex, Index.fromZeroBased(0));

        assertThrows(CommandException.class, () -> deleteLessonCommand.execute(model));
    }
    
}