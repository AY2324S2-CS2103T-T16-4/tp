package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_STUDENTS;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.Lesson;
import seedu.address.model.student.Student;

/**
 * Deletes a lesson to the student identified using it's displayed index from the address book.
 */

public class DeleteLessonCommand extends Command {
    public static final String COMMAND_WORD = "remove";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes a lesson to the student identified "
            + "by the index number used in the last person listing. "
            + "Parameters: INDEX (must be a positive integer) "
            + "INDEX (order in which the lesson appears)\n"
            + "Example: " + COMMAND_WORD + " 1 "
            + "|" + " 1 ";
    public static final String MESSAGE_DELETE_LESSON_SUCCESS = "Deleted lesson from student: %1$s";
    private final Index lessonIndex;
    private final Index index;
    /***
     * Creates a DeleteLessonCommand to delete the lesson from the specified {@code Person},
     */
    public DeleteLessonCommand(Index subjectIndex, Index index) {
        this.lessonIndex = subjectIndex;
        this.index = index;
    }

    /**
     * To execute given a model.
     * @param model {@code Model} which the command should operate on.
     * @return The command result.
     * @throws CommandException If error during execution.
     */
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(index.getZeroBased());

        // Directly access and modify the ObservableList
        ObservableList<Lesson> lessonList = studentToEdit.getObservableList();
        if (lessonIndex.getZeroBased() >= lessonList.size()) {
            throw new CommandException("The lesson index provided is invalid.");
        }
        lessonList.remove((int) lessonIndex.getZeroBased());

        model.updateFilteredStudentList(PREDICATE_SHOW_ALL_STUDENTS);

        return new CommandResult(String.format(MESSAGE_DELETE_LESSON_SUCCESS, studentToEdit.getName()));
    }

    private String generateSuccessMessage(Student editedStudent) {
        return String.format(MESSAGE_DELETE_LESSON_SUCCESS, Messages.format(editedStudent));
    }

}

