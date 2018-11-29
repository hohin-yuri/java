package com.itechart.call.me.library.service.interf;

import com.itechart.call.me.library.entity.Attachment;

public interface AttachmentServiceInterface {
    public Attachment getContactPhones(Long contactId);
    public Attachment getPhoneById(Long id);
    public Attachment addPhone(Attachment phone);
    public void deletePhone(Attachment phone);
}
