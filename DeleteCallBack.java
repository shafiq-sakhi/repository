
package repository;


public interface DeleteCallBack {
    public abstract void onDeleteAlertResult();
    public default void closeOpenedDialog(){}
}
