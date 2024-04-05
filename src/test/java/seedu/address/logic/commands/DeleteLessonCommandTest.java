package seedu.address.logic.commands;


import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalStudents.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.student.Student;


class DeleteLessonCommandTest {

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
    @Test
    public void execute_invalidStudentIndex_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredStudentList().size() + 1);
        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(outOfBoundIndex, Index.fromZeroBased(0));

        assertThrows(CommandException.class, () -> deleteLessonCommand.execute(model));
    }

    @Test
    public void execute_invalidLessonIndex_throwsCommandException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Student studentToEdit = model.getFilteredStudentList().get(0); // Assuming this student exists
        Index studentIndex = Index.fromZeroBased(0);
        Index outOfBoundLessonIndex = Index.fromOneBased(studentToEdit
                .getLessons().size() + 1); // Non-existent lesson index

        DeleteLessonCommand deleteLessonCommand = new DeleteLessonCommand(outOfBoundLessonIndex, studentIndex);

        assertCommandFailure(deleteLessonCommand, model, "The lesson index provided is invalid.");
    }




}

