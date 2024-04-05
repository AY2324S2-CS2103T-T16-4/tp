package seedu.address.logic.parser;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteLessonCommand;
import seedu.address.logic.parser.exceptions.ParseException;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

public class DeleteLessonCommandParser implements Parser<DeleteLessonCommand> {
    public DeleteLessonCommand parse(String args) throws ParseException {
        String parts[] = args.trim().split("\\|");

        try {
            Index Index = ParserUtil.parseIndex(parts[0]);
            Index lessonIndex = ParserUtil.parseIndex(parts[1]);
            return new DeleteLessonCommand(lessonIndex, Index);
        } catch (ParseException pe) {
            throw new ParseException(
                    String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLessonCommand.MESSAGE_USAGE), pe);
        }
    }
}
