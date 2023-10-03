package dz.gouv.ppas.web.cagapps.tools;

public class StaticData {
    /**
     * Data Base Schema Static Names
     */
    public class DataBaseSchema {
        public final static String ADMIN_SCHEMA = "admin";
        public final static String APPS_SCHEMA = "apps";
    }

    /**
     * All Requests Mapping Names
     */
    public class ApiMapping {
        public final static String USER_ROOT = "/users";
        public final static String ROLE_ROOT = "/roles";
        public final static String MENU_ROOT = "/menus";
        public final static String ORGANISATION_ROOT = "/organisations";
        public final static String ORGANISATION_TYPE_ROOT = "/organisationTypes";
        public final static String SESSION_CAG_ROOT = "/sessionCAGs";
        public final static String SESSION_CAG_CONFIG_ROOT = "/sessionCAGsConfig";
        public final static String SESSION_CAG_FILES_ROOT = "/sessionCAGFiles";
        public final static String SESSION_CAG_INVITATION_ROOT = "/sessionCAGInvitations";
        public final static String SESSION_CAG_NOTES_ROOT = "/sessionCAGNotes";
        public final static String SESSION_CAG_FILES_NOTES_ROOT = "/sessionCAGFilesNotes";
        public final static String STATUS_ROOT = "/status";
        public final static String RESOLUTIONS_ROOT = "/Resolutions";
        public final static String STATUS_SESSIONS_ROOT = "/statusSessions";
        public final static String CODE_VALIDATION_ROOT = "/codeValidations";
        public final static String ORGANISATION_MEMBERS_ROOT = "/organisationMembres";
        public final static String SESSION_PLANIF_ROOT = "/planifications";
    }
}
