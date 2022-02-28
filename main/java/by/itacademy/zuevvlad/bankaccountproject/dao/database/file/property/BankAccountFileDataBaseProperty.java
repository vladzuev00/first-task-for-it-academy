package by.itacademy.zuevvlad.bankaccountproject.dao.database.file.property;

public enum BankAccountFileDataBaseProperty
{   //TODO: заменить путь(возможно через classpath)
    BANK_ACCOUNT_FILE_PATH_DATA_BASE("C:\\Users\\Ania\\Desktop\\learning\\java\\it_academy_2\\first_task\\src\\main\\resources\\bank_account_file_data_base.txt"),
    BANK_ACCOUNT_FILE_PATH_WITH_LAST_GENERATED_ID("C:\\Users\\Ania\\Desktop\\learning\\java\\it_academy_2\\first_task\\src\\main\\resources\\last_generated_id\\last_generated_id_of_bank_account.txt");

    private final String value;

    private BankAccountFileDataBaseProperty(final String value)
    {
        this.value = value;
    }

    public final String getValue()
    {
        return this.value;
    }
}
