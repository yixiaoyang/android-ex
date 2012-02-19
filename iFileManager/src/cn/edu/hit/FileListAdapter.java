package cn.edu.hit;

import java.io.File;
import java.util.List;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class FileListAdapter extends BaseAdapter {
	private LayoutInflater inflater;
	
	/*define the icons of diffrent type*/
	private Bitmap folder_icon;
	private Bitmap text_icon;
	private Bitmap music_icon;
	private Bitmap media_icon;
	private Bitmap pdf_icon;
	private Bitmap ppt_icon;
	private Bitmap excel_icon;
	private Bitmap other_icon;
	private Bitmap image_icon;
	
	/*files container*/
	private List<FileUnit> files;
	
	
	public FileListAdapter(Context context, List<FileUnit> files) {
		inflater = LayoutInflater.from(context);
		this.files = files;
		
		/*init of icons*/
		folder_icon = BitmapFactory.decodeResource(context.getResources(),R.drawable.folder_snow);
		text_icon = BitmapFactory.decodeResource(context.getResources(),R.drawable.text);
		music_icon = BitmapFactory.decodeResource(context.getResources(),R.drawable.music);
		media_icon = BitmapFactory.decodeResource(context.getResources(),R.drawable.media);
		pdf_icon = BitmapFactory.decodeResource(context.getResources(),R.drawable.pdf);
		ppt_icon = BitmapFactory.decodeResource(context.getResources(),R.drawable.ppt);
		excel_icon = BitmapFactory.decodeResource(context.getResources(),R.drawable.excel);
		image_icon = BitmapFactory.decodeResource(context.getResources(),R.drawable.picture);
		other_icon = BitmapFactory.decodeResource(context.getResources(),R.drawable.other);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return files.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return files.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup groupView) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		if(convertView == null){
			/*using your own layout(filelist_item.xml)*/
			convertView = inflater.inflate(R.layout.filelist_item, null);
			
			/*seetting the file view*/
			holder = new ViewHolder();
			holder.f_title = (TextView) convertView.findViewById(R.id.textViewFtitle);
			holder.f_size = (TextView) convertView.findViewById(R.id.textViewFsize);
			holder.f_icon = (ImageView) convertView.findViewById(R.id.imageViewFicon);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder) convertView.getTag();
		}
		
		File f = new File(files.get(position).getPath());
		/*if(f == null){
			holder.f_title.setText(R.string.no_title);
			holder.f_icon.setImageBitmap(null);
			return convertView;
		}*/
		
		/*setting name of file(folder) */
		holder.f_title.setText(f.getName());
		
		String fType = FileHandle.getMIMEType(f);
		if(f.isDirectory()){
			holder.f_title.setText(f.getName());
			holder.f_size.setText("");
			holder.f_icon.setImageBitmap(folder_icon);
		}else{
			/* 设置文件的大小 */
			holder.f_size.setText(files.get(position).getFileSize());
			/* 根据文件类型设置文件的图标 */
			if ("image".equals(fType)) {
				holder.f_icon.setImageBitmap(image_icon);
			} else if ("audio".equals(fType)) {
				holder.f_icon.setImageBitmap(music_icon);
			} else if ("video".equals(fType)) {
				holder.f_icon.setImageBitmap(media_icon);
			} else if ("excel".equals(fType)) {
				holder.f_icon.setImageBitmap(excel_icon);
			} else if ("powerpoint".equals(fType)) {
				holder.f_icon.setImageBitmap(ppt_icon);
			} else if ("txt".equals(fType)) {
				holder.f_icon.setImageBitmap(text_icon);
			} else if ("pdf".equals(fType)) {
				holder.f_icon.setImageBitmap(pdf_icon);
			}  else {
				holder.f_icon.setImageBitmap(other_icon);
			}
		}
		return convertView;
	}
	
	/* 不单独写get set可以提高效率 */
	private class ViewHolder {
		TextView f_title;
		TextView f_size;
		ImageView f_icon;
	}
}
