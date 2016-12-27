package com.kuiper.loadItem.bo;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;

import com.kuiper.loadItem.constants.Constant;
import com.kuiper.loadItem.dao.ItemDownloadDAO;
import com.kuiper.loadItem.model.Item;
import com.kuiper.loadItem.model.ItemDimension;
import com.kuiper.loadItem.model.ItemList;

public class ItemDownloadBO {
	final static Logger logger = Logger.getLogger(ItemDownloadBO.class);

	public int process(ItemList itemList) {
		int ret = Constant.FAILURE;

		List<Item> itemlst = itemList.getItemlst();
		for (Item item : itemlst) {
			validate(item);
			ItemDownloadDAO itemDownloadDAO = new ItemDownloadDAO();
			ret = itemDownloadDAO.loadItem(item);
		}
		logger.info("Process returns :" + ret);

		return ret;
	}

	public int validate(Item item) {

		// for (Item item : itemlist) {
		logger.info("Item Dim:" + item.getItemDimension());
		logger.info("Item Number : " + item.get_id());
		if (item.getItemDimension() == null) {
			ItemDimension idim = new ItemDimension();
			idim.setDepth((float) 10.0);
			idim.setHeight((float) 10.0);
			idim.setUom("INCH");
			idim.setWidth((float) 10.0);
			item.setItemDimension(idim);
			// }
			udpateModifier(item);
		}
		return Constant.SUCCESS;
	}

	public void udpateModifier(Item item) {
		Date date = new Date();
		item.setLast_modified_ts(new Timestamp(date.getTime()));

		item.setLast_modified_prg(Constant.IDPRG_NAME);
		item.setLast_modified_user(Constant.DFTL_USRID);
	}
}
