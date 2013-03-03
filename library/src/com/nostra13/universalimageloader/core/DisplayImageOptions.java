package com.nostra13.universalimageloader.core;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;

import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.BitmapDisplayer;
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

/**
 * Contains options for image display. Defines:
 * <ul>
 * <li>whether stub image will be displayed in {@link android.widget.ImageView ImageView} during image loading</li>
 * <li>whether stub image will be displayed in {@link android.widget.ImageView ImageView} if empty URI is passed</li>
 * <li>whether {@link android.widget.ImageView ImageView} should be reset before image loading start</li>
 * <li>whether loaded image will be cached in memory</li>
 * <li>whether loaded image will be cached on disc</li>
 * <li>image scale type</li>
 * <li>bitmap decoding configuration</li>
 * <li>delay before loading of image</li>
 * <li>how decoded {@link Bitmap} will be displayed</li>
 * </ul>
 * 
 * You can create instance:
 * <ul>
 * <li>with {@link Builder}:<br />
 * <b>i.e.</b> :
 * <code>new {@link DisplayImageOptions}.{@link Builder#Builder() Builder()}.{@link Builder#cacheInMemory() cacheInMemory()}.
 * {@link Builder#showStubImage(int) showStubImage()}.{@link Builder#build() build()}</code><br />
 * </li>
 * <li>or by static method: {@link #createSimple()}</li> <br />
 * 
 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
 */
public final class DisplayImageOptions {

	private final int stubImage;
	private final String stubGraphic;
	private final Drawable stubBitmap;
	private final int imageForEmptyUri;
	private final boolean resetViewBeforeLoading;
	private final boolean cacheInMemory;
	private final boolean cacheOnDisc;
	private final ImageScaleType imageScaleType;
	private final Bitmap.Config bitmapConfig;
	private final int delayBeforeLoading;
	private final BitmapDisplayer displayer;
	
	final String userAgent;
	final String username;
	final String password;

	private DisplayImageOptions(Builder builder) {
		stubImage = builder.stubImage;
		stubGraphic = builder.stubGraphic;
		stubBitmap = builder.stubBitmap;
		imageForEmptyUri = builder.imageForEmptyUri;
		resetViewBeforeLoading = builder.resetViewBeforeLoading;
		cacheInMemory = builder.cacheInMemory;
		cacheOnDisc = builder.cacheOnDisc;
		imageScaleType = builder.imageScaleType;
		bitmapConfig = builder.bitmapConfig;
		delayBeforeLoading = builder.delayBeforeLoading;
		displayer = builder.displayer;
		userAgent = builder.userAgent;
		username = builder.username;
		password = builder.password;
	}

	boolean isShowStubImage() {
		return stubImage != 0;
	}
	
	boolean isShowStubGraphic() {
		return stubGraphic != null;
	}
	
	boolean isShowStubBitmap() {
		return stubBitmap != null;
	}

	boolean isShowImageForEmptyUri() {
		return imageForEmptyUri != 0;
	}

	Integer getStubImage() {
		return stubImage;
	}
	
	String getStubGraphic() {
		return stubGraphic;
	}
	
	Drawable getStubBitmap() {
		return stubBitmap;
	}

	Integer getImageForEmptyUri() {
		return imageForEmptyUri;
	}

	boolean isResetViewBeforeLoading() {
		return resetViewBeforeLoading;
	}

	boolean isCacheInMemory() {
		return cacheInMemory;
	}

	boolean isCacheOnDisc() {
		return cacheOnDisc;
	}

	ImageScaleType getImageScaleType() {
		return imageScaleType;
	}

	Bitmap.Config getBitmapConfig() {
		return bitmapConfig;
	}

	boolean isDelayBeforeLoading() {
		return delayBeforeLoading > 0;
	}

	int getDelayBeforeLoading() {
		return delayBeforeLoading;
	}

	BitmapDisplayer getDisplayer() {
		return displayer;
	}
	
	String getUserAgent() {
		return userAgent;
	}
	
	String getUsername() {
		return username;
	}
	
	String getPassword() {
		return password;
	}

	/**
	 * Builder for {@link DisplayImageOptions}
	 * 
	 * @author Sergey Tarasevich (nostra13[at]gmail[dot]com)
	 */
	public static class Builder {
		private int stubImage = 0;
		private String stubGraphic = null;
		private Drawable stubBitmap = null;
		private int imageForEmptyUri = 0;
		private boolean resetViewBeforeLoading = false;
		private boolean cacheInMemory = false;
		private boolean cacheOnDisc = false;
		private ImageScaleType imageScaleType = ImageScaleType.IN_SAMPLE_POWER_OF_2;
		private Bitmap.Config bitmapConfig = Bitmap.Config.ARGB_8888;
		private int delayBeforeLoading = 0;
		private BitmapDisplayer displayer = DefaultConfigurationFactory.createBitmapDisplayer();

		private String userAgent = null;
		private String username = null;
		private String password = null;
		
		/**
		 * Stub image will be displayed in {@link android.widget.ImageView ImageView} during image loading
		 * 
		 * @param stubImageRes Stub image resource
		 */
		public Builder showStubImage(int stubImageRes) {
			stubImage = stubImageRes;
			return this;
		}
		
		public Builder showStubGraphic(String stubImageUrl) {
			stubGraphic = stubImageUrl;
			return this;
		}
		
		public Builder showStubBitmap(Drawable stubImageBmd) {
			stubBitmap = stubImageBmd;
			return this;
		}

		/**
		 * Image will be displayed in {@link android.widget.ImageView ImageView} if empty URI (null or empty string)
		 * will be passed to <b>ImageLoader.displayImage(...)</b> method.
		 * 
		 * @param imageRes Image resource
		 */
		public Builder showImageForEmptyUri(int imageRes) {
			imageForEmptyUri = imageRes;
			return this;
		}

		/** {@link android.widget.ImageView ImageView} will be reset (set <b>null</b>) before image loading start */
		public Builder resetViewBeforeLoading() {
			resetViewBeforeLoading = true;
			return this;
		}

		/** Loaded image will be cached in memory */
		public Builder cacheInMemory() {
			cacheInMemory = true;
			return this;
		}

		/** Loaded image will be cached on disc */
		public Builder cacheOnDisc() {
			cacheOnDisc = true;
			return this;
		}

		/**
		 * Sets {@link ImageScaleType decoding type} for image loading task. Default value -
		 * {@link ImageScaleType#POWER_OF_2}
		 */
		public Builder imageScaleType(ImageScaleType imageScaleType) {
			this.imageScaleType = imageScaleType;
			return this;
		}

		/** Sets {@link Bitmap.Config bitmap config} for image decoding. Default value - {@link Bitmap.Config#ARGB_8888} */
		public Builder bitmapConfig(Bitmap.Config bitmapConfig) {
			this.bitmapConfig = bitmapConfig;
			return this;
		}

		/** Sets delay time before starting loading task. Default - no delay. */
		public Builder delayBeforeLoading(int delayInMillis) {
			this.delayBeforeLoading = delayInMillis;
			return this;
		}

		/**
		 * Sets custom {@link BitmapDisplayer displayer} for image loading task. Default value -
		 * {@link DefaultConfigurationFactory#createBitmapDisplayer()}
		 */
		public Builder displayer(BitmapDisplayer displayer) {
			this.displayer = displayer;
			return this;
		}
		
		public Builder userAgent(String userAgent) {
			this.userAgent = userAgent;
			return this;
		}
		
		public Builder username(String username) {
			this.username = username;
			return this;
		}
		
		public Builder password(String password) {
			this.password = password;
			return this;
		}

		/** Sets all options equal to incoming options */
		public Builder cloneFrom(DisplayImageOptions options) {
			stubImage = options.stubImage;
			stubBitmap = options.stubBitmap;
			imageForEmptyUri = options.imageForEmptyUri;
			resetViewBeforeLoading = options.resetViewBeforeLoading;
			cacheInMemory = options.cacheInMemory;
			cacheOnDisc = options.cacheOnDisc;
			imageScaleType = options.imageScaleType;
			bitmapConfig = options.bitmapConfig;
			delayBeforeLoading = options.delayBeforeLoading;
			displayer = options.displayer;
			userAgent = options.userAgent;
			username = options.username;
			password = options.password;
			return this;
		}

		/** Builds configured {@link DisplayImageOptions} object */
		public DisplayImageOptions build() {
			return new DisplayImageOptions(this);
		}
	}

	/**
	 * Creates options appropriate for single displaying:
	 * <ul>
	 * <li>View will <b>not</b> be reset before loading</li>
	 * <li>Loaded image will <b>not</b> be cached in memory</li>
	 * <li>Loaded image will <b>not</b> be cached on disc</li>
	 * <li>{@link ImageScaleType#IN_SAMPLE_POWER_OF_2} decoding type will be used</li>
	 * <li>{@link Bitmap.Config#ARGB_8888} bitmap config will be used for image decoding</li>
	 * <li>{@link SimpleBitmapDisplayer} will be used for image displaying</li>
	 * </ul>
	 * 
	 * These option are appropriate for simple single-use image (from drawables or from Internet) displaying.
	 */
	public static DisplayImageOptions createSimple() {
		return new Builder().build();
	}
}
