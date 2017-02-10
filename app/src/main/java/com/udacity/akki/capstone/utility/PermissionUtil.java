package com.udacity.akki.capstone.utility;

import android.Manifest;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.provider.Settings;
import android.support.v13.app.FragmentCompat;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AlertDialog;

/**
 * Created by 836158 on 07-02-2017.
 */

public final class PermissionUtil {

    public static boolean checkPermission(Context context, String permission) {
        return ActivityCompat.checkSelfPermission(context, permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static boolean checkPermissions(Context context, String... permissions) {
        for (String permission : permissions) {
            if (!checkPermission(context, permission)) {
                return false;
            }
        }
        return true;
    }

    public static boolean isLocationAccessGranted(Context context) {

        String[] permissions = new String[]{Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION};

        return checkPermissions(context, permissions);
    }

    public static void requestPermissions(Object o, int permissionId, String... permissions) {
        if (o instanceof Fragment) {
            FragmentCompat.requestPermissions((Fragment) o, permissions, permissionId);
        } else if (o instanceof Activity) {
            ActivityCompat.requestPermissions((Activity) o, permissions, permissionId);
        }
    }

    public static Boolean shouldShowRequestPermissionRationale(Object o, String permission) {
        if (o instanceof Fragment) {
            return FragmentCompat.shouldShowRequestPermissionRationale((Fragment) o, permission);
        } else
            return o instanceof Activity && ActivityCompat.shouldShowRequestPermissionRationale((Activity) o, permission);

    }

    public static void showReasonForPermission(Context context, final Object o, String permission) {
        String permissionMessage = "Go to settings to give permissions.";
        switch (permission) {
            case Manifest.permission.READ_PHONE_STATE:
                permissionMessage = "Without phone state permissions, user cannot be registered for offers or notifications. Go to settings to give permissions";
                break;
            case Manifest.permission.ACCESS_FINE_LOCATION:
            case Manifest.permission.ACCESS_COARSE_LOCATION:
                permissionMessage = "Cannot access without location permissions. Go to settings to give permissions";
                break;
            case Manifest.permission.CAMERA:
                permissionMessage = "Cannot scan without access to camera. Go to settings to give permissions";
                break;
            default:
                break;
        }
        showDialogBox(context, o, permissionMessage);
        //showSnackBar(context,o,permissionMessage);
    }

    private static void showDialogBox(Context context, final Object o, String permissionMessage) {
        new AlertDialog.Builder(context)
                .setTitle("Permission Required")
                .setMessage(permissionMessage)
                .setPositiveButton("Settings", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // continue with delete
                        if (o instanceof Fragment) {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", ((Fragment) o).getActivity().getPackageName(), null);
                            intent.setData(uri);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                            ((Fragment) o).getActivity().finishAffinity();
                            ((Fragment) o).getActivity().startActivity(intent);
                        } else if (o instanceof Activity) {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", ((Activity) o).getPackageName(), null);
                            intent.setData(uri);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                            ((Activity) o).finishAffinity();
                            ((Activity) o).startActivity(intent);
                        } else if (o instanceof android.support.v4.app.Fragment) {
                            Intent intent = new Intent();
                            intent.setAction(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
                            Uri uri = Uri.fromParts("package", ((android.support.v4.app.Fragment) o).getActivity().getPackageName(), null);
                            intent.setData(uri);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                            intent.addFlags(Intent.FLAG_ACTIVITY_EXCLUDE_FROM_RECENTS);
                            ((android.support.v4.app.Fragment) o).getActivity().finishAffinity();
                            ((android.support.v4.app.Fragment) o).getActivity().startActivity(intent);
                        }


                    }
                })
                .setNegativeButton("Back", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        // do nothing

                        if (o instanceof Fragment) {
                            ((Fragment) o).getActivity().finishAffinity();

                        } else if (o instanceof Activity) {
                            ((Activity) o).finish();

                        }
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert)
                .show();

    }

}
