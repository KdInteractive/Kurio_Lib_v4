/*
 * This file is auto-generated.  DO NOT MODIFY.
 * Original file: /home/lionel/Projects/kurio_lib_v4/src/main/aidl/com/cide/interactive/parentalArea/Api/IRemoteApi.aidl
 */
package com.cide.interactive.parentalArea.Api;
public interface IRemoteApi extends android.os.IInterface
{
/** Local-side IPC implementation stub class. */
public static abstract class Stub extends android.os.Binder implements com.cide.interactive.parentalArea.Api.IRemoteApi
{
private static final java.lang.String DESCRIPTOR = "com.cide.interactive.parentalArea.Api.IRemoteApi";
/** Construct the stub at attach it to the interface. */
public Stub()
{
this.attachInterface(this, DESCRIPTOR);
}
/**
 * Cast an IBinder object into an com.cide.interactive.parentalArea.Api.IRemoteApi interface,
 * generating a proxy if needed.
 */
public static com.cide.interactive.parentalArea.Api.IRemoteApi asInterface(android.os.IBinder obj)
{
if ((obj==null)) {
return null;
}
android.os.IInterface iin = obj.queryLocalInterface(DESCRIPTOR);
if (((iin!=null)&&(iin instanceof com.cide.interactive.parentalArea.Api.IRemoteApi))) {
return ((com.cide.interactive.parentalArea.Api.IRemoteApi)iin);
}
return new com.cide.interactive.parentalArea.Api.IRemoteApi.Stub.Proxy(obj);
}
@Override public android.os.IBinder asBinder()
{
return this;
}
@Override public boolean onTransact(int code, android.os.Parcel data, android.os.Parcel reply, int flags) throws android.os.RemoteException
{
switch (code)
{
case INTERFACE_TRANSACTION:
{
reply.writeString(DESCRIPTOR);
return true;
}
case TRANSACTION_deleteUser:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
boolean _result = this.deleteUser(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_getAllUserId:
{
data.enforceInterface(DESCRIPTOR);
java.util.List _result = this.getAllUserId();
reply.writeNoException();
reply.writeList(_result);
return true;
}
case TRANSACTION_authorizeAppsForUsers:
{
data.enforceInterface(DESCRIPTOR);
java.util.List _arg0;
java.lang.ClassLoader cl = (java.lang.ClassLoader)this.getClass().getClassLoader();
_arg0 = data.readArrayList(cl);
java.util.List _arg1;
_arg1 = data.readArrayList(cl);
int _arg2;
_arg2 = data.readInt();
boolean _arg3;
_arg3 = (0!=data.readInt());
this.authorizeAppsForUsers(_arg0, _arg1, _arg2, _arg3);
reply.writeNoException();
return true;
}
case TRANSACTION_setCategoryForApp:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _arg2;
_arg2 = data.readString();
this.setCategoryForApp(_arg0, _arg1, _arg2);
reply.writeNoException();
return true;
}
case TRANSACTION_getAppCategory:
{
data.enforceInterface(DESCRIPTOR);
java.util.List _result = this.getAppCategory();
reply.writeNoException();
reply.writeList(_result);
return true;
}
case TRANSACTION_getAppForCategory:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.util.List _result = this.getAppForCategory(_arg0);
reply.writeNoException();
reply.writeList(_result);
return true;
}
case TRANSACTION_getCategoryIdForApp:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
java.lang.String _result = this.getCategoryIdForApp(_arg0, _arg1);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_getCategories:
{
data.enforceInterface(DESCRIPTOR);
java.util.Map _result = this.getCategories();
reply.writeNoException();
reply.writeMap(_result);
return true;
}
case TRANSACTION_getDefaultAppsToEnable:
{
data.enforceInterface(DESCRIPTOR);
java.util.List _result = this.getDefaultAppsToEnable();
reply.writeNoException();
reply.writeList(_result);
return true;
}
case TRANSACTION_getDefaultAppsToDisable:
{
data.enforceInterface(DESCRIPTOR);
java.util.List _result = this.getDefaultAppsToDisable();
reply.writeNoException();
reply.writeList(_result);
return true;
}
case TRANSACTION_getAppsToCategorize:
{
data.enforceInterface(DESCRIPTOR);
java.util.List _result = this.getAppsToCategorize();
reply.writeNoException();
reply.writeList(_result);
return true;
}
case TRANSACTION_setParentPreferences:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
this.setParentPreferences(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_getParentPreferencesForKey:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _result = this.getParentPreferencesForKey(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_needToReload:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
int _arg1;
_arg1 = data.readInt();
this.needToReload(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_importAppsFromOtherChild:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
this.importAppsFromOtherChild(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_createChildFromJson:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
int _result = this.createChildFromJson(_arg0);
reply.writeNoException();
reply.writeInt(_result);
return true;
}
case TRANSACTION_getChildAsJson:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
java.lang.String _result = this.getChildAsJson(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_saveChildInfoFromJson:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
int _arg1;
_arg1 = data.readInt();
this.saveChildInfoFromJson(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_getTimeSlotForUser:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
java.lang.String _result = this.getTimeSlotForUser(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_saveTimeSlotsForUser:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.saveTimeSlotsForUser(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_saveTimeSlotForUser:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.saveTimeSlotForUser(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_importTimeSlots:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
this.importTimeSlots(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_getWebListForUser:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
java.lang.String _result = this.getWebListForUser(_arg0);
reply.writeNoException();
reply.writeString(_result);
return true;
}
case TRANSACTION_addWebListForUsers:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.addWebListForUsers(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_deleteWebList:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.deleteWebList(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_deleteWebListForUser:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
this.deleteWebListForUser(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_updateWebList:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
int _arg1;
_arg1 = data.readInt();
this.updateWebList(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_updateWebListUrl:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
this.updateWebListUrl(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_importWebListFromChild:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
int _arg1;
_arg1 = data.readInt();
this.importWebListFromChild(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_enabledGoogleAccount:
{
data.enforceInterface(DESCRIPTOR);
boolean _arg0;
_arg0 = (0!=data.readInt());
int _arg1;
_arg1 = data.readInt();
this.enabledGoogleAccount(_arg0, _arg1);
reply.writeNoException();
return true;
}
case TRANSACTION_isGoogleAccountEnabled:
{
data.enforceInterface(DESCRIPTOR);
int _arg0;
_arg0 = data.readInt();
boolean _result = this.isGoogleAccountEnabled(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_appsNeedGoogleAccount:
{
data.enforceInterface(DESCRIPTOR);
java.util.List _arg0;
java.lang.ClassLoader cl = (java.lang.ClassLoader)this.getClass().getClassLoader();
_arg0 = data.readArrayList(cl);
boolean _result = this.appsNeedGoogleAccount(_arg0);
reply.writeNoException();
reply.writeInt(((_result)?(1):(0)));
return true;
}
case TRANSACTION_increaseAnalyticsUsage:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
this.increaseAnalyticsUsage(_arg0);
reply.writeNoException();
return true;
}
case TRANSACTION_timeManagementUsedOnce:
{
data.enforceInterface(DESCRIPTOR);
this.timeManagementUsedOnce();
reply.writeNoException();
return true;
}
case TRANSACTION_increaseAppAnalyticsValueForKey:
{
data.enforceInterface(DESCRIPTOR);
java.lang.String _arg0;
_arg0 = data.readString();
java.lang.String _arg1;
_arg1 = data.readString();
this.increaseAppAnalyticsValueForKey(_arg0, _arg1);
reply.writeNoException();
return true;
}
}
return super.onTransact(code, data, reply, flags);
}
private static class Proxy implements com.cide.interactive.parentalArea.Api.IRemoteApi
{
private android.os.IBinder mRemote;
Proxy(android.os.IBinder remote)
{
mRemote = remote;
}
@Override public android.os.IBinder asBinder()
{
return mRemote;
}
public java.lang.String getInterfaceDescriptor()
{
return DESCRIPTOR;
}
/**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
@Override public boolean deleteUser(int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_deleteUser, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.List getAllUserId() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getAllUserId, _data, _reply, 0);
_reply.readException();
java.lang.ClassLoader cl = (java.lang.ClassLoader)this.getClass().getClassLoader();
_result = _reply.readArrayList(cl);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void authorizeAppsForUsers(java.util.List activityInfoToEnable, java.util.List appsToDisable, int userId, boolean forDefaultList) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeList(activityInfoToEnable);
_data.writeList(appsToDisable);
_data.writeInt(userId);
_data.writeInt(((forDefaultList)?(1):(0)));
mRemote.transact(Stub.TRANSACTION_authorizeAppsForUsers, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
//app Category

@Override public void setCategoryForApp(java.lang.String packageName, java.lang.String activityName, java.lang.String categroyId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(packageName);
_data.writeString(activityName);
_data.writeString(categroyId);
mRemote.transact(Stub.TRANSACTION_setCategoryForApp, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public java.util.List getAppCategory() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getAppCategory, _data, _reply, 0);
_reply.readException();
java.lang.ClassLoader cl = (java.lang.ClassLoader)this.getClass().getClassLoader();
_result = _reply.readArrayList(cl);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.List getAppForCategory(java.lang.String categoryId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(categoryId);
mRemote.transact(Stub.TRANSACTION_getAppForCategory, _data, _reply, 0);
_reply.readException();
java.lang.ClassLoader cl = (java.lang.ClassLoader)this.getClass().getClassLoader();
_result = _reply.readArrayList(cl);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String getCategoryIdForApp(java.lang.String packageName, java.lang.String activityName) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(packageName);
_data.writeString(activityName);
mRemote.transact(Stub.TRANSACTION_getCategoryIdForApp, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.Map getCategories() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.Map _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getCategories, _data, _reply, 0);
_reply.readException();
java.lang.ClassLoader cl = (java.lang.ClassLoader)this.getClass().getClassLoader();
_result = _reply.readHashMap(cl);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.List getDefaultAppsToEnable() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getDefaultAppsToEnable, _data, _reply, 0);
_reply.readException();
java.lang.ClassLoader cl = (java.lang.ClassLoader)this.getClass().getClassLoader();
_result = _reply.readArrayList(cl);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.List getDefaultAppsToDisable() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getDefaultAppsToDisable, _data, _reply, 0);
_reply.readException();
java.lang.ClassLoader cl = (java.lang.ClassLoader)this.getClass().getClassLoader();
_result = _reply.readArrayList(cl);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.util.List getAppsToCategorize() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.util.List _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_getAppsToCategorize, _data, _reply, 0);
_reply.readException();
java.lang.ClassLoader cl = (java.lang.ClassLoader)this.getClass().getClassLoader();
_result = _reply.readArrayList(cl);
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
//PArentPreference

@Override public void setParentPreferences(java.lang.String key, java.lang.String value) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(key);
_data.writeString(value);
mRemote.transact(Stub.TRANSACTION_setParentPreferences, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public java.lang.String getParentPreferencesForKey(java.lang.String key) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(key);
mRemote.transact(Stub.TRANSACTION_getParentPreferencesForKey, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
//to replace DBrequestHelper

@Override public void needToReload(java.lang.String keyToReload, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(keyToReload);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_needToReload, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
//import

@Override public void importAppsFromOtherChild(int currentChildId, int otherChildId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(currentChildId);
_data.writeInt(otherChildId);
mRemote.transact(Stub.TRANSACTION_importAppsFromOtherChild, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public int createChildFromJson(java.lang.String childInfoAsJson) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
int _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(childInfoAsJson);
mRemote.transact(Stub.TRANSACTION_createChildFromJson, _data, _reply, 0);
_reply.readException();
_result = _reply.readInt();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public java.lang.String getChildAsJson(int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_getChildAsJson, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void saveChildInfoFromJson(java.lang.String childAsJson, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(childAsJson);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_saveChildInfoFromJson, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public java.lang.String getTimeSlotForUser(int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_getTimeSlotForUser, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void saveTimeSlotsForUser(java.lang.String timeSlot) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(timeSlot);
mRemote.transact(Stub.TRANSACTION_saveTimeSlotsForUser, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void saveTimeSlotForUser(java.lang.String timeSlot) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(timeSlot);
mRemote.transact(Stub.TRANSACTION_saveTimeSlotForUser, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void importTimeSlots(int fromUserId, int toUserId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(fromUserId);
_data.writeInt(toUserId);
mRemote.transact(Stub.TRANSACTION_importTimeSlots, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public java.lang.String getWebListForUser(int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
java.lang.String _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_getWebListForUser, _data, _reply, 0);
_reply.readException();
_result = _reply.readString();
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void addWebListForUsers(java.lang.String webListInfoAsJson) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(webListInfoAsJson);
mRemote.transact(Stub.TRANSACTION_addWebListForUsers, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void deleteWebList(java.lang.String webListInfoAsJson) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(webListInfoAsJson);
mRemote.transact(Stub.TRANSACTION_deleteWebList, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void deleteWebListForUser(int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_deleteWebListForUser, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void updateWebList(java.lang.String webListInfoAsJson, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(webListInfoAsJson);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_updateWebList, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void updateWebListUrl(java.lang.String oldUrl, java.lang.String newUrl) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(oldUrl);
_data.writeString(newUrl);
mRemote.transact(Stub.TRANSACTION_updateWebListUrl, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void importWebListFromChild(int fromChild, int toChild) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(fromChild);
_data.writeInt(toChild);
mRemote.transact(Stub.TRANSACTION_importWebListFromChild, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void enabledGoogleAccount(boolean enabled, int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(((enabled)?(1):(0)));
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_enabledGoogleAccount, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public boolean isGoogleAccountEnabled(int userId) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeInt(userId);
mRemote.transact(Stub.TRANSACTION_isGoogleAccountEnabled, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public boolean appsNeedGoogleAccount(java.util.List appsToCheck) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
boolean _result;
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeList(appsToCheck);
mRemote.transact(Stub.TRANSACTION_appsNeedGoogleAccount, _data, _reply, 0);
_reply.readException();
_result = (0!=_reply.readInt());
}
finally {
_reply.recycle();
_data.recycle();
}
return _result;
}
@Override public void increaseAnalyticsUsage(java.lang.String usageKey) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(usageKey);
mRemote.transact(Stub.TRANSACTION_increaseAnalyticsUsage, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void timeManagementUsedOnce() throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
mRemote.transact(Stub.TRANSACTION_timeManagementUsedOnce, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
@Override public void increaseAppAnalyticsValueForKey(java.lang.String packageName, java.lang.String key) throws android.os.RemoteException
{
android.os.Parcel _data = android.os.Parcel.obtain();
android.os.Parcel _reply = android.os.Parcel.obtain();
try {
_data.writeInterfaceToken(DESCRIPTOR);
_data.writeString(packageName);
_data.writeString(key);
mRemote.transact(Stub.TRANSACTION_increaseAppAnalyticsValueForKey, _data, _reply, 0);
_reply.readException();
}
finally {
_reply.recycle();
_data.recycle();
}
}
}
static final int TRANSACTION_deleteUser = (android.os.IBinder.FIRST_CALL_TRANSACTION + 0);
static final int TRANSACTION_getAllUserId = (android.os.IBinder.FIRST_CALL_TRANSACTION + 1);
static final int TRANSACTION_authorizeAppsForUsers = (android.os.IBinder.FIRST_CALL_TRANSACTION + 2);
static final int TRANSACTION_setCategoryForApp = (android.os.IBinder.FIRST_CALL_TRANSACTION + 3);
static final int TRANSACTION_getAppCategory = (android.os.IBinder.FIRST_CALL_TRANSACTION + 4);
static final int TRANSACTION_getAppForCategory = (android.os.IBinder.FIRST_CALL_TRANSACTION + 5);
static final int TRANSACTION_getCategoryIdForApp = (android.os.IBinder.FIRST_CALL_TRANSACTION + 6);
static final int TRANSACTION_getCategories = (android.os.IBinder.FIRST_CALL_TRANSACTION + 7);
static final int TRANSACTION_getDefaultAppsToEnable = (android.os.IBinder.FIRST_CALL_TRANSACTION + 8);
static final int TRANSACTION_getDefaultAppsToDisable = (android.os.IBinder.FIRST_CALL_TRANSACTION + 9);
static final int TRANSACTION_getAppsToCategorize = (android.os.IBinder.FIRST_CALL_TRANSACTION + 10);
static final int TRANSACTION_setParentPreferences = (android.os.IBinder.FIRST_CALL_TRANSACTION + 11);
static final int TRANSACTION_getParentPreferencesForKey = (android.os.IBinder.FIRST_CALL_TRANSACTION + 12);
static final int TRANSACTION_needToReload = (android.os.IBinder.FIRST_CALL_TRANSACTION + 13);
static final int TRANSACTION_importAppsFromOtherChild = (android.os.IBinder.FIRST_CALL_TRANSACTION + 14);
static final int TRANSACTION_createChildFromJson = (android.os.IBinder.FIRST_CALL_TRANSACTION + 15);
static final int TRANSACTION_getChildAsJson = (android.os.IBinder.FIRST_CALL_TRANSACTION + 16);
static final int TRANSACTION_saveChildInfoFromJson = (android.os.IBinder.FIRST_CALL_TRANSACTION + 17);
static final int TRANSACTION_getTimeSlotForUser = (android.os.IBinder.FIRST_CALL_TRANSACTION + 18);
static final int TRANSACTION_saveTimeSlotsForUser = (android.os.IBinder.FIRST_CALL_TRANSACTION + 19);
static final int TRANSACTION_saveTimeSlotForUser = (android.os.IBinder.FIRST_CALL_TRANSACTION + 20);
static final int TRANSACTION_importTimeSlots = (android.os.IBinder.FIRST_CALL_TRANSACTION + 21);
static final int TRANSACTION_getWebListForUser = (android.os.IBinder.FIRST_CALL_TRANSACTION + 22);
static final int TRANSACTION_addWebListForUsers = (android.os.IBinder.FIRST_CALL_TRANSACTION + 23);
static final int TRANSACTION_deleteWebList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 24);
static final int TRANSACTION_deleteWebListForUser = (android.os.IBinder.FIRST_CALL_TRANSACTION + 25);
static final int TRANSACTION_updateWebList = (android.os.IBinder.FIRST_CALL_TRANSACTION + 26);
static final int TRANSACTION_updateWebListUrl = (android.os.IBinder.FIRST_CALL_TRANSACTION + 27);
static final int TRANSACTION_importWebListFromChild = (android.os.IBinder.FIRST_CALL_TRANSACTION + 28);
static final int TRANSACTION_enabledGoogleAccount = (android.os.IBinder.FIRST_CALL_TRANSACTION + 29);
static final int TRANSACTION_isGoogleAccountEnabled = (android.os.IBinder.FIRST_CALL_TRANSACTION + 30);
static final int TRANSACTION_appsNeedGoogleAccount = (android.os.IBinder.FIRST_CALL_TRANSACTION + 31);
static final int TRANSACTION_increaseAnalyticsUsage = (android.os.IBinder.FIRST_CALL_TRANSACTION + 32);
static final int TRANSACTION_timeManagementUsedOnce = (android.os.IBinder.FIRST_CALL_TRANSACTION + 33);
static final int TRANSACTION_increaseAppAnalyticsValueForKey = (android.os.IBinder.FIRST_CALL_TRANSACTION + 34);
}
/**
     * Demonstrates some basic types that you can use as parameters
     * and return values in AIDL.
     */
public boolean deleteUser(int userId) throws android.os.RemoteException;
public java.util.List getAllUserId() throws android.os.RemoteException;
public void authorizeAppsForUsers(java.util.List activityInfoToEnable, java.util.List appsToDisable, int userId, boolean forDefaultList) throws android.os.RemoteException;
//app Category

public void setCategoryForApp(java.lang.String packageName, java.lang.String activityName, java.lang.String categroyId) throws android.os.RemoteException;
public java.util.List getAppCategory() throws android.os.RemoteException;
public java.util.List getAppForCategory(java.lang.String categoryId) throws android.os.RemoteException;
public java.lang.String getCategoryIdForApp(java.lang.String packageName, java.lang.String activityName) throws android.os.RemoteException;
public java.util.Map getCategories() throws android.os.RemoteException;
public java.util.List getDefaultAppsToEnable() throws android.os.RemoteException;
public java.util.List getDefaultAppsToDisable() throws android.os.RemoteException;
public java.util.List getAppsToCategorize() throws android.os.RemoteException;
//PArentPreference

public void setParentPreferences(java.lang.String key, java.lang.String value) throws android.os.RemoteException;
public java.lang.String getParentPreferencesForKey(java.lang.String key) throws android.os.RemoteException;
//to replace DBrequestHelper

public void needToReload(java.lang.String keyToReload, int userId) throws android.os.RemoteException;
//import

public void importAppsFromOtherChild(int currentChildId, int otherChildId) throws android.os.RemoteException;
public int createChildFromJson(java.lang.String childInfoAsJson) throws android.os.RemoteException;
public java.lang.String getChildAsJson(int userId) throws android.os.RemoteException;
public void saveChildInfoFromJson(java.lang.String childAsJson, int userId) throws android.os.RemoteException;
public java.lang.String getTimeSlotForUser(int userId) throws android.os.RemoteException;
public void saveTimeSlotsForUser(java.lang.String timeSlot) throws android.os.RemoteException;
public void saveTimeSlotForUser(java.lang.String timeSlot) throws android.os.RemoteException;
public void importTimeSlots(int fromUserId, int toUserId) throws android.os.RemoteException;
public java.lang.String getWebListForUser(int userId) throws android.os.RemoteException;
public void addWebListForUsers(java.lang.String webListInfoAsJson) throws android.os.RemoteException;
public void deleteWebList(java.lang.String webListInfoAsJson) throws android.os.RemoteException;
public void deleteWebListForUser(int userId) throws android.os.RemoteException;
public void updateWebList(java.lang.String webListInfoAsJson, int userId) throws android.os.RemoteException;
public void updateWebListUrl(java.lang.String oldUrl, java.lang.String newUrl) throws android.os.RemoteException;
public void importWebListFromChild(int fromChild, int toChild) throws android.os.RemoteException;
public void enabledGoogleAccount(boolean enabled, int userId) throws android.os.RemoteException;
public boolean isGoogleAccountEnabled(int userId) throws android.os.RemoteException;
public boolean appsNeedGoogleAccount(java.util.List appsToCheck) throws android.os.RemoteException;
public void increaseAnalyticsUsage(java.lang.String usageKey) throws android.os.RemoteException;
public void timeManagementUsedOnce() throws android.os.RemoteException;
public void increaseAppAnalyticsValueForKey(java.lang.String packageName, java.lang.String key) throws android.os.RemoteException;
}
