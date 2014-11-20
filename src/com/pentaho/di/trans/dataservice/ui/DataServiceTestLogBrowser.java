/*!
* PENTAHO CORPORATION PROPRIETARY AND CONFIDENTIAL
*
* Copyright 2002 - 2014 Pentaho Corporation (Pentaho). All rights reserved.
*
* NOTICE: All information including source code contained herein is, and
* remains the sole property of Pentaho and its licensors. The intellectual
* and technical concepts contained herein are proprietary and confidential
* to, and are trade secrets of Pentaho and may be covered by U.S. and foreign
* patents, or patents in process, and are protected by trade secret and
* copyright laws. The receipt or possession of this source code and/or related
* information does not convey or imply any rights to reproduce, disclose or
* distribute its contents, or to manufacture, use, or sell anything that it
* may describe, in whole or in part. Any reproduction, modification, distribution,
* or public display of this information without the express written authorization
* from Pentaho is strictly prohibited and in violation of applicable laws and
* international treaties. Access to the source code contained herein is strictly
* prohibited to anyone except those individuals and entities who have executed
* confidentiality and non-disclosure agreements or other agreements with Pentaho,
* explicitly covering such access.
*/

package com.pentaho.di.trans.dataservice.ui;

import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.layout.FormAttachment;
import org.eclipse.swt.layout.FormData;
import org.eclipse.swt.layout.FormLayout;
import org.eclipse.swt.widgets.Composite;
import org.pentaho.di.core.logging.HasLogChannelInterface;
import org.pentaho.di.core.logging.LogChannelInterface;
import org.pentaho.di.core.logging.LogLevel;
import org.pentaho.di.core.logging.LogParentProvidedInterface;
import org.pentaho.di.ui.spoon.trans.LogBrowser;

public class DataServiceTestLogBrowser {

  private final StyledText logText;

  public DataServiceTestLogBrowser( Composite parentComposite ) {
    logText = addStyledText( parentComposite );
  }

  public void attachToLogBrowser( final LogChannelInterface logChannel,
                                  LogLevel logLevel ) {
    logText.setText( "" );
    logChannel.setLogLevel( logLevel );
    LogBrowser logBrowser = new LogBrowser( logText, new LogParentProvidedInterface() {
      @Override
      public HasLogChannelInterface getLogChannelProvider() {
        return new HasLogChannelInterface() {
          @Override
          public LogChannelInterface getLogChannel() {
            return logChannel;
          }
        };
      }
    } );
    logBrowser.installLogSniffer();
  }

  private StyledText addStyledText( Composite composite ) {
    composite.setLayout( new FormLayout() );
    StyledText text = new StyledText( composite, SWT.H_SCROLL | SWT.V_SCROLL );
    FormData formData = new FormData();
    formData.left = new FormAttachment( 0, 0 );
    formData.right = new FormAttachment( 100, 0 );
    formData.top = new FormAttachment( 0, 0 );
    formData.bottom = new FormAttachment( 100, 0 );
    text.setLayoutData( formData );
    return text;
  }

}
