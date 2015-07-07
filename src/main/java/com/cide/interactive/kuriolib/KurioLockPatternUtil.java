package com.cide.interactive.kuriolib;

import android.app.admin.DevicePolicyManager;
import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.ServiceManager;
import android.provider.Settings;

import com.android.internal.widget.ILockSettings;
import com.android.internal.widget.LockPatternUtils;
import com.android.internal.widget.LockPatternView;

import java.util.List;

/**
 * Created by leehack on 07/11/14.
 */
public class KurioLockPatternUtil extends LockPatternUtils {
    private ILockSettings mLockSettingsService;

    /**
     * @param context
     */
    public KurioLockPatternUtil(Context context) {
        super(context);
    }

    private ILockSettings getLockSettings() {
        if (mLockSettingsService == null) {
            mLockSettingsService = ILockSettings.Stub.asInterface(
                    (IBinder) ServiceManager.getService("lock_settings"));
        }
        return mLockSettingsService;
    }

    /**
     * Check to see if a pattern matches the saved pattern.  If no pattern exists,
     * always returns true.
     *
     * @param pattern The pattern to check.
     * @param userId  The user id to check.
     * @return Whether the pattern matches the stored one.
     */
    public boolean checkPattern(List<LockPatternView.Cell> pattern, final int userId) {
        try {
            return getLockSettings().checkPattern(patternToString(pattern), userId);
        } catch (RemoteException re) {
            return true;
        }
    }

    /**
     * Check to see if a password matches the saved password.  If no password exists,
     * always returns true.
     *
     * @param password The password to check.
     * @param userId   The user id to check.
     * @return Whether the password matches the stored one.
     */
    public boolean checkPassword(String password, final int userId) {
        try {
            return getLockSettings().checkPassword(password, userId);
        } catch (RemoteException re) {
            return true;
        }
    }

    /**
     * @return Whether the lock password is enabled, or if it is set as a backup for biometric weak
     */
    public boolean isLockParentPasswordEnabled() {
        long mode = getLong(PASSWORD_TYPE_KEY, 0);
        long backupMode = getLong(PASSWORD_TYPE_ALTERNATE_KEY, 0);
        final boolean passwordEnabled = mode == DevicePolicyManager.PASSWORD_QUALITY_ALPHABETIC
                || mode == DevicePolicyManager.PASSWORD_QUALITY_NUMERIC
                || mode == DevicePolicyManager.PASSWORD_QUALITY_ALPHANUMERIC
                || mode == DevicePolicyManager.PASSWORD_QUALITY_COMPLEX;
        final boolean backupEnabled = backupMode == DevicePolicyManager.PASSWORD_QUALITY_ALPHABETIC
                || backupMode == DevicePolicyManager.PASSWORD_QUALITY_NUMERIC
                || backupMode == DevicePolicyManager.PASSWORD_QUALITY_ALPHANUMERIC
                || backupMode == DevicePolicyManager.PASSWORD_QUALITY_COMPLEX;

        return savedPasswordExists() && (passwordEnabled ||
                (usingBiometricWeak() && backupEnabled));
    }

    /**
     * @return Whether the lock pin is enabled, or if it is set as a backup for biometric weak
     */
    public boolean isLockParentPinEnabled() {
        long mode = getLong(PASSWORD_TYPE_KEY, 0);
        long backupMode = getLong(PASSWORD_TYPE_ALTERNATE_KEY, 0);
        final boolean passwordEnabled = mode == DevicePolicyManager.PASSWORD_QUALITY_NUMERIC;
        final boolean backupEnabled = backupMode == DevicePolicyManager.PASSWORD_QUALITY_NUMERIC;

        return savedPasswordExists() && (passwordEnabled ||
                (usingBiometricWeak() && backupEnabled));
    }

    /**
     * Check to see if the user has stored a lock pattern.
     * @return Whether a saved pattern exists.
     */
    public boolean savedPasswordExists() {
        try {
            return getLockSettings().havePassword(0);
        } catch (RemoteException re) {
            return false;
        }
    }

    /**
     * @return Whether the lock pattern is enabled, or if it is set as a backup for biometric weak
     */
    public boolean isLockParentPatternEnabled() {
        final boolean backupEnabled =
                getLong(PASSWORD_TYPE_ALTERNATE_KEY, DevicePolicyManager.PASSWORD_QUALITY_SOMETHING)
                        == DevicePolicyManager.PASSWORD_QUALITY_SOMETHING;

        return getBoolean(Settings.Secure.LOCK_PATTERN_ENABLED, false)
                && (getLong(PASSWORD_TYPE_KEY, DevicePolicyManager.PASSWORD_QUALITY_SOMETHING)
                == DevicePolicyManager.PASSWORD_QUALITY_SOMETHING ||
                (usingBiometricWeak() && backupEnabled));
    }

    private long getLong(String secureSettingKey, long defaultValue) {
        try {
            return getLockSettings().getLong(secureSettingKey, defaultValue, 0);
        } catch (RemoteException re) {
            return defaultValue;
        }
    }

    private boolean getBoolean(String secureSettingKey, boolean defaultValue) {
        return getBoolean(secureSettingKey, defaultValue, 0);
    }

    private boolean getBoolean(String secureSettingKey, boolean defaultValue, int userId) {
        try {
            return getLockSettings().getBoolean(secureSettingKey, defaultValue, userId);
        } catch (RemoteException re) {
            return defaultValue;
        }
    }
}
