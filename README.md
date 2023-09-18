# Secret Diary App 📔

## Description 📝

The Diary App is an Android application that allows users to keep a digital diary. Users can write, save, and remove diary entries. Each entry includes a date and the user's text. The app provides a simple and efficient way to record personal thoughts and experiences.

## Features ✨

### Writing Diary Entries ✍️

- Users can type diary entries in the provided EditText field.
- Entries can be any text that the user wishes to record.
- Diary entries include a date and time stamp indicating when they were added.

### Saving Diary Entries 💾

- Users can save their diary entries by clicking the "Save" button.
- Saved entries are displayed in a TextView, with the most recent entry at the top.
- Entries are automatically timestamped with the date and time they were saved.

### Undo Last Entry 🔄

- Users can undo the last diary entry by clicking the "Undo" button.
- A confirmation dialog appears to ensure the user wants to delete the last entry.
- Deleted entries cannot be recovered once confirmed.

### Data Persistence 📂

- Diary entries are saved locally using SharedPreferences, allowing them to persist between app sessions.
- The app loads the saved entries when started, ensuring the user's diary is always accessible.

## Usage 🚀

1. Type a diary entry in the provided EditText field.
2. Click the "Save" button to save the entry.
3. The saved entry is displayed in the TextView with a timestamp.
4. To undo the last entry, click the "Undo" button and confirm the deletion.

## Enjoy documenting your thoughts and memories with the Diary App! 📖
