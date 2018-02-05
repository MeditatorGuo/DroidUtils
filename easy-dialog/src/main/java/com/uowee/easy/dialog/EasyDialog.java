package com.uowee.easy.dialog;


import android.app.Dialog;
import android.content.Context;
import android.support.constraint.ConstraintLayout;
import android.support.v4.content.ContextCompat;
import android.view.View;
import android.widget.TextView;

/**
 * Created by GuoWee on 2018/2/1.
 */

public class EasyDialog {

    public static class Builder {

        private Context mContext;
        private Dialog mDialog;
        private ViewHolder mViewHolder;

        public Builder(Context context) {
            this.mContext = context;
        }

        public Builder setTitle(CharSequence title) {
            mViewHolder.tvTitle.setText(title);
            return this;
        }


        public Builder setTitle(CharSequence title, int color) {
            mViewHolder.tvTitle.setText(title);
            mViewHolder.tvTitle.setTextColor(ContextCompat.getColor(mContext, color));
            return this;
        }

        public Builder setTitle(int resid) {
            mViewHolder.tvTitle.setText(resid);
            return this;
        }

        public Builder setTitle(int resid, int color) {
            mViewHolder.tvTitle.setText(resid);
            mViewHolder.tvTitle.setTextColor(ContextCompat.getColor(mContext, color));
            return this;
        }

        public Builder setMessage(CharSequence title) {
            mViewHolder.tvMessage.setText(title);
            return this;
        }

        public Builder setMessage(CharSequence title, int color) {
            mViewHolder.tvMessage.setText(title);
            mViewHolder.tvMessage.setTextColor(ContextCompat.getColor(mContext, color));
            return this;
        }

        public Builder setMessage(int resid) {
            mViewHolder.tvMessage.setText(resid);
            return this;
        }

        public Builder setMessage(int resid, int color) {
            mViewHolder.tvMessage.setText(resid);
            mViewHolder.tvMessage.setTextColor(ContextCompat.getColor(mContext, color));
            return this;
        }

        public Builder setPositiveButton(CharSequence text, final View.OnClickListener listener) {
            mViewHolder.tvPositiveBtn.setVisibility(View.VISIBLE);
            mViewHolder.tvPositiveBtn.setText(text);
            mViewHolder.tvPositiveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDialog.dismiss();
                    if (listener != null) {
                        listener.onClick(view);
                    }
                }
            });
            return this;
        }

        public Builder setPositiveButton(CharSequence text, final View.OnClickListener listener, int color) {
            mViewHolder.tvPositiveBtn.setVisibility(View.VISIBLE);
            mViewHolder.tvPositiveBtn.setText(text);
            mViewHolder.tvPositiveBtn.setTextColor(ContextCompat.getColor(mContext, color));
            mViewHolder.tvPositiveBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDialog.dismiss();
                    if (listener != null) {
                        listener.onClick(view);
                    }
                }
            });
            return this;
        }

        public Builder setNegativeButton(CharSequence text, final View.OnClickListener listener) {
            mViewHolder.tvNegativeBtn.setVisibility(View.VISIBLE);
            mViewHolder.tvNegativeBtn.setText(text);
            mViewHolder.tvNegativeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDialog.dismiss();
                    if (listener != null) {
                        listener.onClick(view);
                    }
                }
            });
            return this;
        }

        public Builder setNegativeButton(CharSequence text, final View.OnClickListener listener, int color) {
            mViewHolder.tvNegativeBtn.setVisibility(View.VISIBLE);
            mViewHolder.tvNegativeBtn.setText(text);
            mViewHolder.tvNegativeBtn.setTextColor(ContextCompat.getColor(mContext, color));
            mViewHolder.tvNegativeBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    mDialog.dismiss();
                    if (listener != null) {
                        listener.onClick(view);
                    }
                }
            });
            return this;
        }

        public Builder setCancelable(boolean flag) {
            mDialog.setCancelable(flag);
            return this;
        }

        public Builder setCanceledOnTouchOutside(boolean flag) {
            mDialog.setCanceledOnTouchOutside(flag);
            return this;
        }

        class ViewHolder {
            TextView tvTitle;
            TextView tvMessage;
            TextView tvPositiveBtn, tvNegativeBtn;
            ConstraintLayout layout;
            View line1, line2;

            public ViewHolder(View view) {
                tvTitle = view.findViewById(R.id.dialog_title);
                tvMessage = view.findViewById(R.id.dialog_message);
                tvPositiveBtn = view.findViewById(R.id.dialog_positive);
                tvNegativeBtn = view.findViewById(R.id.dialog_negative);

                layout = view.findViewById(R.id.dialog_layout);
                line1 = view.findViewById(R.id.dialog_line1);
                line2 = view.findViewById(R.id.dialog_line2);
            }

        }
    }


}
