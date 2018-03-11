package economic_freedom.com.likha;

import android.view.View;

/**
 * Created by G50 on 10-Mar-18.
 */
public interface ItemClickListener {


    /**
     * Called when an item is clicked.
     *
     * @param view View of the item that was clicked.
     * @param position  Position of the item that was clicked.
     */

    void onClick(View view, int position);
}
