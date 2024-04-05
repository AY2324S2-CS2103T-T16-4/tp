package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteLessonCommand;

public class DeleteLessonCommandParserTest {
    private DeleteLessonCommandParser parser = new DeleteLessonCommandParser();

    @Test
    public void parse_invalidArgs_throwsParseException() {
        String userInput = "a|b";

        assertParseFailure(parser, userInput,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteLessonCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_missingStudentIndex_throwsParseException() {
        String userInput = "|1";
        assertParseFailure(parser, userInput, String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeleteLessonCommand.MESSAGE_USAGE));
    }


}
