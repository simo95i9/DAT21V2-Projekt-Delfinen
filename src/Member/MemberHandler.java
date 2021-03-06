/*
 * @author Malthe
 * + a little spice of @author Mark
 * 19/05/2021 10.15
 *
 * DAT21V2-Projekt-Delfinen
 *
 */

package Member;

import Service.UserInput;

import java.time.*;
import java.util.Arrays;
import java.util.List;

import DelfinMain.DelfinMain;
public class MemberHandler {

    public static void addMember(){
        UserInput.clearConsole();
        String tmpText = "";
        String splitter = "\n" + "-";
        UserInput.console.println("Create a member by entering user data" + splitter);

        String name = UserInput.askForName();
        tmpText += "Member name: " + name;
        UserInput.clearConsole();
        UserInput.console.println( tmpText + splitter);

        LocalDate birthDate = UserInput.askForBirthdate();
        tmpText += "\nDate of birth: " + birthDate;
        UserInput.clearConsole();
        UserInput.console.println( tmpText + splitter);

        String address = UserInput.askForAddress();
        tmpText += "\nAddress: " + address;
        UserInput.clearConsole();
        UserInput.console.println( tmpText + splitter);

        String phoneNumber = UserInput.askForPhoneNumber();
        tmpText += "\nPhone number: " + phoneNumber;
        UserInput.clearConsole();
        UserInput.console.println( tmpText + splitter);

        Boolean activeMembership = UserInput.askForActiveMembership();

        Boolean competitiveSwimmer = UserInput.askForCompetitiveStatus();

        Member member = new Member(name, birthDate, address, phoneNumber, activeMembership, competitiveSwimmer);

        DelfinMain.listOfMembers.addNewMember(member);
        UserInput.clearConsole();
        UserInput.console.println("Member added.");
    }

    public static void updateMember() {
        UserInput.clearConsole();
        boolean isRunning = true;

        Member memberToUpdate = UserInput.askForMember();
        UserInput.clearConsole();

        String menuText = "What will you update?";
        List<String> menuOptions = Arrays.asList(
                "Name",
                "Address",
                "Birthdate",
                "Phone number",
                "Membership status",
                "Competitive status",
                "Go back to president menu"
        );

        while (isRunning) {
            UserInput.clearConsole();
            UserInput.console.println("Select which member data to update" + "\n" + "-");
            UserInput.console.println(memberToUpdate.toString());

            Integer menuChoice = UserInput.askForMenuChoice(menuText, menuOptions);
            switch (menuChoice) {
                // TODO: maybe show change and ask for confirmation before changing
                case 0 -> {
                    UserInput.console.println("New name\n_______________");
                    String newName = UserInput.askForName();
                    memberToUpdate.setName(newName);
                    UserInput.clearConsole();
                }
                case 1 -> {
                    UserInput.console.println("New address\n_______________");
                    String newAddress = UserInput.askForAddress();
                    memberToUpdate.setAddress(newAddress);
                    UserInput.clearConsole();
                }
                case 2 -> {
                    UserInput.console.println("New birthday\n_______________");
                    LocalDate birthDate = UserInput.askForBirthdate();
                    memberToUpdate.setBirthDate(birthDate);
                    UserInput.clearConsole();
                }
                case 3 -> {
                    UserInput.console.println("New phone number\n_______________");
                    String newPhoneNumber = UserInput.askForPhoneNumber();
                    memberToUpdate.setPhoneNumber(newPhoneNumber);
                    UserInput.clearConsole();
                }
                case 4 -> {
                    UserInput.console.println("New membership status\n_______________");
                    Boolean newMembershipStatus = UserInput.askForActiveMembership();
                    memberToUpdate.setActiveMember(newMembershipStatus);
                    UserInput.clearConsole();
                }
                case 5 -> {
                    UserInput.console.println("New competition status\n_______________");
                    Boolean newCompetitionStatus = UserInput.askForCompetitiveStatus();
                    memberToUpdate.setIsCompetitive(newCompetitionStatus);
                    UserInput.clearConsole();
                }
                case 6 -> {
                    isRunning = false;
                    UserInput.clearConsole();
                }
            }

            DelfinMain.listOfMembers.updateMember(memberToUpdate);
        }
            UserInput.clearConsole();
    }

    public static void removeMember() {
        //TODO: if there is no members, disallow access
        UserInput.clearConsole();

        Member memberToRemove = UserInput.askForMember();

        Boolean confirmRemoval = UserInput.textio.newBooleanInputReader()
                .read("Confirm removal of: " + memberToRemove.getName());

        if (confirmRemoval){
            DelfinMain.listOfMembers.removeMember(memberToRemove.getMemberId());
        } else {
            UserInput.console.println("Terminating....");
        }
            UserInput.clearConsole();
    }

    public static void displayMemberList() {
        UserInput.clearConsole();
        var listOfIds = DelfinMain.listOfMembers.getFieldFromAllMembers(Member -> Member.getName() + ": with member id: "
                + Member.getMemberId() + ". Is member active: " + Member.isActiveMember() + ". Is member competitive: " + Member.getIsCompetitive() + "\n");
        if (listOfIds.isEmpty()){
            UserInput.console.println("There are no members");
        } else {
            UserInput.console.println(String.valueOf(listOfIds));
        }
    }
}
